package it.ezzie.weathery.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.ui.theme.DarkNavyBlue
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WeatherDetail(){
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            //Real Temperature
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
                    .padding(24.dp),

                ){
                Image(
                    painter = painterResource(id = R.drawable.thermometer),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Start),
                    colorFilter = ColorFilter.tint(color = White)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Feel Like",
                    fontSize = 14.sp,
                    color = Silver,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "29",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            //NE Wind
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
                    .padding(24.dp),

                ){
                Image(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Start)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "NE Wind",
                    fontSize = 14.sp,
                    color = Silver,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "8 mi/h",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
        }
        //Second Roll
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            //Humidity
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
                    .padding(24.dp),

                ){
                Image(
                    painter = painterResource(id = R.drawable.humidity),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Start)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Humidity",
                    fontSize = 14.sp,
                    color = Silver,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "61 %",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            //NE Wind
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
                    .padding(24.dp),

                ){
                Image(
                    painter = painterResource(id = R.drawable.ultraviolet),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Start),
                    colorFilter = ColorFilter.tint(color = White)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "UV",
                    fontSize = 14.sp,
                    color = Silver,
                    modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "0",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
        }
        //Third Roll
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            //Visibility
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
                    .padding(24.dp),

                ){
                Image(
                    painter = painterResource(id = R.drawable.visibility),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Start),
                    colorFilter = ColorFilter.tint(color = White)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Visibility",
                    fontSize = 14.sp,
                    color = Silver,
                    modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "9 km",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            //NE Wind
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .background(color = DarkNavyBlue, RoundedCornerShape(10.dp))
                    .padding(24.dp),

                ){
                Image(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Start),
                    colorFilter = ColorFilter.tint(color = White)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Air Pressure",
                    fontSize = 14.sp,
                    color = Silver,
                    modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "1013 Psa",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}