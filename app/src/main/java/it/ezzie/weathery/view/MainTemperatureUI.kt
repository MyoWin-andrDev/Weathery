package it.ezzie.weathery.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.dataReturn.WeatherCondition
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
fun MainTemperatureUI(temperature : Double, weatherCode : Int){
    val weatherCondition = WeatherCondition()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ){
        //Temperature and Weather Code
        Column {
            Text(
                text = "${Math.round(temperature)}\u00B0",
                fontSize = 56.sp,
                color = White,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Spacer(modifier = Modifier.height(8.dp))
            val condition = weatherCondition.codeToCondition(weatherCode)
            Text(
                text = condition,
                fontSize = 20.sp,
                color = Silver,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = weatherCondition.codeToIcon(weatherCode)),
            contentDescription = null,
            modifier = Modifier
                     .size(130.dp),
        )
    }
}

