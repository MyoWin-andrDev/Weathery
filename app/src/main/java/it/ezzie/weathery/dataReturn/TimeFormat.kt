package it.ezzie.weathery.dataReturn

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimeFormat {
    @RequiresApi(Build.VERSION_CODES.O)
    fun HourFormatter (time : String) : String{
        val hour = LocalTime.parse(time.substringAfter("T"))
        val formatter = DateTimeFormatter.ofPattern("h:m a")
        val formattedHour = hour.format(formatter).uppercase()
        return formattedHour
    }

    fun secondToHour(duration : Double) : String {
        val totalMinute = duration / 60
        val hour = Math.round(totalMinute / 60)
        val minute = Math.round(totalMinute % 60)
        val totalDuration = "${hour} h ${minute} m"
        return totalDuration
    }
}