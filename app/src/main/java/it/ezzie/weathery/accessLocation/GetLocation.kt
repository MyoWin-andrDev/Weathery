package it.ezzie.weathery.accessLocation


import android.location.LocationManager
import com.google.android.gms.location.LocationRequest
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import it.ezzie.weathery.MainActivity


class GetLocation : Activity() {
    val LOCATION_REQUEST_CODE: Int = 123
    val REQUEST_CHECK_SETTINGS: Int = 124
    lateinit var locationRequest: LocationRequest
    var locationInterface : LocationCallBackInterface? = null

    interface LocationCallBackInterface{
       fun onLocationReceived(latitude : Double, longitude : Double)
    }
    fun setLocationCallBackInterface(callback : LocationCallBackInterface){
        this.locationInterface = callback
    }
//    var latitude: Double? = null
//    var longitude: Double? = null

    fun initLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 2000 // Update interval in milliseconds
            fastestInterval = 1000 // Fastest update interval
        }
    }

    fun initData(context : Context) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
        } else {
            if (isGPSEnable(context)) {
                startLocationUpdates(context)
            } else {
                turnOnGPS(context)
            }
        }
    }

    fun isGPSEnable(context : Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun turnOnGPS(context: Context) {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(context)
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                Toast.makeText(context, "GPS is already turned on", Toast.LENGTH_SHORT).show()
                startLocationUpdates(context)
            } catch (e: ApiException) {
                if (e.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                    try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(context as Activity, REQUEST_CHECK_SETTINGS)
                    } catch (ex: IntentSender.SendIntentException) {
                        ex.printStackTrace()
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(context: Context) {
        LocationServices.getFusedLocationProviderClient(context)
            .requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    if (locationResult != null && locationResult.locations.size > 0) {
                        locationResult.let {
                            val index = it.locations.size - 1
                            val latitude = it.locations[index].latitude
                            val longitude = it.locations[index].longitude
                            Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
                            locationInterface?.onLocationReceived(latitude, longitude)
                        }
                    }
                }
            }, Looper.getMainLooper())
    }

//Unwanted Code
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        val context = super.getApplicationContext()
//        val activity = super.activity
//        if (requestCode == LOCATION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (isGPSEnable(context)) {
//                    startLocationUpdates(activity)
//                } else {
//                    turnOnGPS(context)
//                }
//            } else {
//                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

}