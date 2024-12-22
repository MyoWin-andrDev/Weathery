package it.ezzie.weathery.accessLocation


import android.location.LocationManager
import com.google.android.gms.location.LocationRequest
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes



class GetLocation : FragmentActivity(){
    private val LOCATION_REQUEST_CODE: Int = 123
    private val REQUEST_CHECK_SETTINGS: Int = 124
    private lateinit var locationRequest: LocationRequest
    private var latitude: Double? = null
    private var longitude: Double? = null

    private fun initLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 2000 // Update interval in milliseconds
            fastestInterval = 1000 // Fastest update interval
        }
    }

    private fun initData() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
        } else {
            if (isGPSEnable()) {
                startLocationUpdates()
            } else {
                turnOnGPS()
            }
        }
    }

    private fun isGPSEnable(): Boolean {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun turnOnGPS() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(this)
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                Toast.makeText(this, "GPS is already turned on", Toast.LENGTH_SHORT).show()
                startLocationUpdates()
            } catch (e: ApiException) {
                if (e.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                    try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                    } catch (ex: IntentSender.SendIntentException) {
                        ex.printStackTrace()
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() : Pair<Double?, Double?> {
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult : LocationResult) {
                    super.onLocationResult(locationResult)
                    if (locationResult != null && locationResult.locations.size > 0) {
                        locationResult.let {
                            val index = it.locations.size - 1
                            latitude = it.locations[index].latitude
                            longitude = it.locations[index].longitude
                            Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
                        }
                    }
                }
            }, Looper.getMainLooper())
        return Pair(latitude , longitude)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isGPSEnable()) {
                    startLocationUpdates()
                } else {
                    turnOnGPS()
                }
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}