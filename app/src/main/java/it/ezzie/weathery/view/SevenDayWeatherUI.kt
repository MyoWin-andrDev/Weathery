package it.ezzie.weathery.view

import android.os.Build
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
fun SevenDayWeatherUI(date : String, weatherIcon : Int, weatherCode : String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 16.dp, bottom = 16.dp, end = 24.dp)
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
        Text(
            text = "13\u00B0/23\u00B0",
            color = White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateToDayOfWeek(date : String) : String {
    val sevenDays = LocalDate.parse(date)
    return sevenDays.dayOfWeek.toString().lowercase().capitalize()
}