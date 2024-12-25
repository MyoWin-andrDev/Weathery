package it.ezzie.weathery

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import android.window.SplashScreenView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.accessLocation.GetLocation
import it.ezzie.weathery.dataReturn.CurrentHourIndex
import it.ezzie.weathery.dataReturn.TimeFormat
import it.ezzie.weathery.dataReturn.WeatherCondition
import it.ezzie.weathery.model.geoData.GeoData
import it.ezzie.weathery.model.weatherData.Daily
import it.ezzie.weathery.model.weatherData.Hourly
import it.ezzie.weathery.model.weatherData.WeatherData
import it.ezzie.weathery.networkAPI.GeoCodingNetworkClient
import it.ezzie.weathery.networkAPI.WeatherNetworkClient
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.WeatheryTheme
import it.ezzie.weathery.ui.theme.Yellow
import it.ezzie.weathery.view.HeadingUI
import it.ezzie.weathery.view.HourlyWeatherUI
import it.ezzie.weathery.view.MainTemperatureUI
import it.ezzie.weathery.view.SevenDayWeatherUI
import it.ezzie.weathery.view.SunriseSunset
import it.ezzie.weathery.view.TodayDetailUI
import it.ezzie.weathery.view.WeatherDetail
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    var keepSplashScreen = true
    val latitude = intent.getDoubleExtra("latitude", 0.0)
    val longitude = intent.getDoubleExtra("longitude", 0.0)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatheryTheme {
                WeatherScreen(latitude, longitude)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(latitude : Double, longitude : Double){
    val weatherNetworkClient = WeatherNetworkClient()
    val geoCodingNetworkClient = GeoCodingNetworkClient()
    val coroutineScope = rememberCoroutineScope()
    var weatherData by remember {
        mutableStateOf <WeatherData?>(null)
    }
    var geoData by remember {
        mutableStateOf<GeoData?>(null)
    }
    val geoLocation = remember {
        GetLocation()
    }
    geoLocation.initLocationRequest()

    LaunchedEffect(Unit) {
        coroutineScope.launch {

                weatherData = weatherNetworkClient.getWeatherInfo(13.7563, 100.5018)
           // }
        }
    }

    weatherData?.let { data ->
    if(latitude != null) {
        Log.d("intent", "$latitude & $longitude")
    }
        //Getting Current Hour
        val hour = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("ha")
        val currentHour = hour.format(formatter).uppercase()

        Box{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = DarkerNavyBlue)
                    .verticalScroll(rememberScrollState())
            ){
                HeadingUI()
                MainTemperatureUI(data.current.temperature_2m , data.current.weather_code)
                TodayDetailUI(data.current, data.current_units)
                Title(text = "Today")
                Spacer(modifier = Modifier.height(8.dp))
                HourlyWeatherRow(data.hourly)
                Title(text = "Next 7 days")
                Spacer(modifier = Modifier.height(8.dp))
                SevenDayWeatherColumn(data.daily)
                Title(text = "$currentHour Weather details")
                Spacer(modifier = Modifier.height(8.dp))
                WeatherDetailBox(data)
                SunDetail(data.daily)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherRow(hourly: Hourly){
    val currentHourIndex = CurrentHourIndex().getCurrentHour(LocalDateTime.now().toString().substringBefore("T"))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ){
        hourly.time.subList(currentHourIndex!!,25).forEachIndexed { index, _ ->
            val formattedHour : String?
            //Formatting Hour (01:00) to (1AM)
            val hour = LocalTime.parse(hourly.time[currentHourIndex + index].substringAfter("T"))
            val formatter = DateTimeFormatter.ofPattern("ha")
            formattedHour = if(currentHourIndex + index == 24){
                "TMR"
            } else{
                hour.format(formatter).uppercase()
            }
            //WeatherCode To Icon
            val icon : Int = WeatherCondition().codeToIcon(hourly.weather_code[currentHourIndex + index])
            //Temperature
            val temperature = hourly.temperature_2m[currentHourIndex + index]
            val formattedTemperature = Math.round(temperature).toString() + "\u00B0"
            HourlyWeatherUI(formattedHour, icon, formattedTemperature)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SevenDayWeatherColumn(daily : Daily){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ){
        daily.time.forEachIndexed { index, timeStamp ->
            val weatherCondition = WeatherCondition()
            val weatherIcon = weatherCondition.codeToIcon(daily.weather_code[index])
            val weatherCode = weatherCondition.codeToCondition(daily.weather_code[index])
            val minTemperature= daily.temperature_2m_min[index]
            val maxTemperature= daily.temperature_2m_max[index]
            SevenDayWeatherUI(timeStamp, weatherIcon, weatherCode, minTemperature, maxTemperature)
        }
    }
}

@Composable
fun Title(text : String){
    Text(
        text = text,
        fontSize = 18.sp,
        color = Yellow,
        fontFamily = FontFamily(Font(R.font.lato_regular)),
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(start = 24.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherDetailBox(weatherData: WeatherData){
    val currentHourIndex = CurrentHourIndex().getCurrentHour(LocalDateTime.now().toString())
   if(currentHourIndex != null){
        val apparentTemperature = weatherData.daily.apparent_temperature_max[0]
        //Getting Compass Direction
        fun getCompassDirection(degrees: Int): String {
            val directions = arrayOf(
                "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
                "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
            )
            val arrayIndex = ((degrees + 11.25) / 22.5) % 16 // Calculate index
            return directions[arrayIndex.toInt()]
        }
        val windDegreeDirection = weatherData.daily.wind_direction_10m_dominant[0]
        val windCompassDirection = getCompassDirection(windDegreeDirection)
        val windSpeed = weatherData.daily.wind_speed_10m_max[0]
        val humidity = weatherData.hourly.relative_humidity_2m[currentHourIndex]
        val uxIndex = weatherData.daily.uv_index_max[0]
        val visibility = weatherData.hourly.visibility[currentHourIndex]
        val airPressure = weatherData.hourly.pressure_msl[currentHourIndex]
        WeatherDetail(apparentTemperature, windCompassDirection, windSpeed, humidity, uxIndex, visibility, airPressure, weatherData)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SunDetail(daily: Daily){
    val timeFormat = TimeFormat()
    val sunrise = timeFormat.HourFormatter(daily.sunrise[0])
    val sunset = timeFormat.HourFormatter(daily.sunset[0])
    val duration = timeFormat.secondToHour(daily.sunshine_duration[0])
    SunriseSunset(sunrise, sunset, duration)

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewWeather(){
    WeatheryTheme {
        //WeatherScreen()
    }
}

