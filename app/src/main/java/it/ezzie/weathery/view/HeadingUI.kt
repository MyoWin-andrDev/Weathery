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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.ezzie.weathery.R
import it.ezzie.weathery.ui.theme.DarkNavyBlue
import it.ezzie.weathery.ui.theme.DarkerNavyBlue
import it.ezzie.weathery.ui.theme.GreyBorder
import it.ezzie.weathery.ui.theme.Silver
import it.ezzie.weathery.ui.theme.White

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HeadingUI() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)

        ) {
            Column {
                //Location Icon and Country
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                            .align(Alignment.CenterVertically),
                        colorFilter = ColorFilter.tint(color = White),

                        )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "BangKok",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        color = White
                    )
                }
                //Date Typo
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "14 December, Saturday",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = Silver
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            //Categories Icon
            Image(
                painter = painterResource(id = R.drawable.categories),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .background(color = DarkerNavyBlue, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                colorFilter = ColorFilter.tint(color = Silver)
            )
        }
    }