package it.ezzie.weathery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.ui.theme.DarkNavyBlue
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.GreyBorder
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.WeatheryTheme
import it.ezzie.weathery.ui.theme.White
import it.ezzie.weathery.view.HeadingUI
import it.ezzie.weathery.view.HourlyWeatherUI
import it.ezzie.weathery.view.MainTemperatureUI
import it.ezzie.weathery.view.SevenDayWeatherUI
import it.ezzie.weathery.view.SunriseSunset
import it.ezzie.weathery.view.TodayDetailUI
import it.ezzie.weathery.view.WeatherDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun HourlyWeatherRow(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
    ) {
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewWeather(){
    WeatheryTheme {
        Box{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = DarkerNavyBlue)
                    .verticalScroll(rememberScrollState())
            ){
                HeadingUI()
                MainTemperatureUI()
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
                HourlyWeatherUI()
                SevenDayWeatherUI()
                WeatherDetail()
                SunriseSunset()
            }
        }
    }
}

