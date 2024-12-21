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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.ui.theme.DarkNavyBlue
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SunriseSunset(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
    ){
        //Sunrise Column
        Column(
            modifier = Modifier.padding(16.dp)
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
                text = "6:32 am",
                fontSize = 16.sp,
                color = White
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        //Sun Logo
        Column(
            modifier = Modifier.padding(top = 48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunny),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        //Sunset Column
        Column(
            modifier = Modifier.padding(16.dp)
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
                text = "Sunrise",
                fontSize = 14.sp,
                color = Silver,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "6:32 am",
                fontSize = 16.sp,
                color = White
            )
        }
    }
}