package it.ezzie.weathery

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.dataReturn.CurrentHourIndex
import it.ezzie.weathery.dataReturn.WeatherCondition
import it.ezzie.weathery.model.Daily
import it.ezzie.weathery.model.Hourly
import it.ezzie.weathery.model.WeatherData
import it.ezzie.weathery.networkAPI.NetworkClient
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.WeatheryTheme
import it.ezzie.weathery.ui.theme.White
import it.ezzie.weathery.view.HeadingUI
import it.ezzie.weathery.view.HourlyWeatherUI
import it.ezzie.weathery.view.MainTemperatureUI
import it.ezzie.weathery.view.SevenDayWeatherUI
import it.ezzie.weathery.view.SunriseSunset
import it.ezzie.weathery.view.TodayDetailUI
import it.ezzie.weathery.view.WeatherDetail
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatheryTheme {
                WeatherScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(){
    val networkClient = NetworkClient()
    val coroutineScope = rememberCoroutineScope()
    var weatherData by remember {
        mutableStateOf <WeatherData?>(null)
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            weatherData = networkClient.getWeatherInfo(13.7563,100.5018)
        }
    }

    weatherData?.let { data ->
        Box{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = DarkerNavyBlue)
                    .verticalScroll(rememberScrollState())
            ){
                HeadingUI(data.current_weather.time)
                MainTemperatureUI(data.current_weather.temperature , data.current_weather.weathercode)
                TodayDetailUI()
                Text(
                    text = "Today",
                    fontSize = 20.sp,
                    color = White,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                HourlyWeatherRow(data.hourly)
                SevenDayWeatherColumn(data.daily)
                WeatherDetail()
                SunriseSunset()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherRow(hourly: Hourly){
    val currentHourIndex = CurrentHourIndex().getCurrentHour(hourly.time[0].substringBefore("T"))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ){
        hourly.time.subList(currentHourIndex!!,25).forEachIndexed { index , currentHour ->
            val formattedHour : String?
            //Formatting Hour (01:00) to (1AM)
            val hour = LocalTime.parse(hourly.time[currentHourIndex + index].substringAfter("T"))
            val formatter = DateTimeFormatter.ofPattern("ha")
            formattedHour = if(currentHourIndex + index == 24){
                "Tomorrow"
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
        modifier = Modifier.fillMaxWidth()
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewWeather(){
    WeatheryTheme {
        WeatherScreen()
    }
}

