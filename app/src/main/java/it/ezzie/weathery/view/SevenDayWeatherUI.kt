package it.ezzie.weathery.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.White

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SevenDayWeatherUI(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 16.dp, bottom = 16.dp, end = 24.dp)
            .background(color = DarkerNavyBlue),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            text = "Sunday",
            fontSize = 16.sp,
            color = White,
            fontFamily = FontFamily(Font(R.font.roboto_medium))
        )
        Spacer(modifier = Modifier.weight(1.5f))
        Image(
            painter = painterResource(id = R.drawable.storm),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "Storm",
            fontSize = 16.sp,
            color = White,
            fontFamily = FontFamily(Font(R.font.roboto_medium))
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "13\u00B0/23\u00B0",
            color = White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    }
}