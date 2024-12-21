package it.ezzie.weathery.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.White
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SevenDayWeatherUI(date : String, weatherIcon : Int, weatherCode : String, minTemperature : Double, maxTemperature : Double){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 16.dp, bottom = 24.dp, end = 24.dp)
            .background(color = DarkerNavyBlue),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = DateToDayOfWeek(date),
            fontSize = 16.sp,
            color = White,
            fontFamily = FontFamily(Font(R.font.roboto_medium))
        )

        Image(
            painter = painterResource(id = weatherIcon),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = weatherCode,
            fontSize = 16.sp,
            color = White,
            fontFamily = FontFamily(Font(R.font.roboto_medium))
        )
        fun RoundingValue(temperature : Double) : String{
            return Math.round(temperature).toString()
        }
        Text(
            text = "${RoundingValue(minTemperature)}\u00B0/${RoundingValue(maxTemperature)}\u00B0",
            color = White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateToDayOfWeek(date : String) : String {
    val sevenDays = LocalDate.parse(date).dayOfWeek
    val today = LocalDate.now().dayOfWeek
    Log.d("SevenDays",sevenDays.toString())
    Log.d("Today",today.toString())
    return if(sevenDays.equals(today)){
        "Today"
    }
    else{
        sevenDays.toString().substring(0,3).lowercase().capitalize()
    }
}