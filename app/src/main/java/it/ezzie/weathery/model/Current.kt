package it.ezzie.weathery.model

data class Current(
    val interval: Int,
    val is_day: Int,
    val precipitation: Double,
    val rain: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val weather_code: Int,
    val wind_direction_10m: Int,
    val wind_speed_10m: Double
)