package it.ezzie.weathery.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.ui.theme.DarkNavyBlue
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
fun SunriseSunset(sunrise : String, sunset : String, duration : String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp)
            .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
    ){
        //Sunrise Column
        Column(
            modifier = Modifier.padding( start = 8.dp, top = 16.dp, bottom = 16.dp, end = 8.dp )
        ){
            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Start),
                colorFilter = ColorFilter.tint(color = White)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Sunrise",
                fontSize = 14.sp,
                color = Silver,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = sunrise,
                fontSize = 16.sp,
                color = White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        //Sun Logo
        Column(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunny),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sunshine Duration",
                fontSize = 14.sp,
                color = Silver,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = duration,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        //Sunset Column
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 8.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.End),
                colorFilter = ColorFilter.tint(color = White)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Sunset",
                fontSize = 14.sp,
                color = Silver,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = sunset,
                fontSize = 16.sp,
                color = White
            )
        }
    }
}