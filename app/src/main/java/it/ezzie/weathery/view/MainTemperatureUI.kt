package it.ezzie.weathery.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
fun MainTemperatureUI(temperature : Double, weatherCode : Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ){
        //Temperature and Weather Code
        Column {
            Text(
                text = "18\u00B0",
                fontSize = 56.sp,
                color = White,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Spacer(modifier = Modifier.height(8.dp))
            val condition = codeToCondition(weatherCode)
            Text(
                text = condition,
                fontSize = 20.sp,
                color = Silver,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.cloudy),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)

        )

    }
}

fun codeToCondition(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> "Clear Sky"
        1 -> "Mainly Clear"
        2 -> "Partly Cloudy"
        3 -> "Overcast"
        45 -> "Fog"
        48 -> "Fog Depositing Rime"
        51 -> "Light Drizzle"
        53 -> "Moderate Drizzle"
        55 -> "Dense Drizzle"
        56 -> "Light Freezing Drizzle"
        57 -> "Dense Freezing Drizzle"
        61 -> "Slight Rain"
        63 -> "Moderate Rain"
        65 -> "Heavy Rain"
        66 -> "Light Freezing Rain"
        67 -> "Heavy Freezing Rain"
        71 -> "Slight Snow Fall"
        73 -> "Moderate Snow Fall"
        75 -> "Heavy Snow Fall"
        77 -> "Snow Grains"
        80 -> "Slight Rain Showers"
        81 -> "Moderate Rain Showers"
        82 -> "Violent Rain Showers"
        85 -> "Slight Snow Showers"
        86 -> "Heavy Snow Showers"
        95 -> "Thunderstorm: Slight or Moderate"
        96 -> "Thunderstorm with Slight Hail"
        99 -> "Thunderstorm with Heavy Hail"
        else -> "Unknown Weather Code"
    }
}

fun codeToIcon(weatherCode: Int) : Int{
    if(weatherCode = ){

    }
    return
}
