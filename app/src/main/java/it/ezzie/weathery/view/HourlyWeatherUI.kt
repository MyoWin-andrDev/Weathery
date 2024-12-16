package it.ezzie.weathery.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.model.CurrentWeather
import it.ezzie.weathery.model.Hourly
import it.ezzie.weathery.model.HourlyUnits
import it.ezzie.weathery.ui.theme.DarkNavyBlue
import it.ezzie.weathery.ui.theme.GreyBorder
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
fun HourlyWeatherUI(hourlyTime : String, weatherCondition : Int, temperature : String, hourlyUnits: HourlyUnits){
    Box(
        modifier = Modifier
            .padding(5.dp)
            .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
//            .border(1.dp,color = GreyBorder, RoundedCornerShape(10.dp))

    ){
        //Hourly Detail
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        ) {
            //Hourly Time
            Text(
                text = hourlyTime,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Silver,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = weatherCondition),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = temperature,
                fontSize = 14.sp,
                color = White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}