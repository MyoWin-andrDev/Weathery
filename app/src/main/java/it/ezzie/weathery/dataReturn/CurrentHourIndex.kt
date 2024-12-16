package it.ezzie.weathery.dataReturn

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
class CurrentHourIndex {

    //Compare Date
    fun getCurrentHour(date : String ) : Int?{
        val localDate = LocalDate.now()
        val parsedDate = LocalDate.parse(date.substringBefore("T"))
        return if(parsedDate == localDate) {
            LocalDateTime.now().hour
        }
        else{
            null
        }
    }
}