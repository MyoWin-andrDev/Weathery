package it.ezzie.weathery.model

data class CurrentUnits(
    val interval: String,
    val is_day: String,
    val precipitation: String,
    val rain: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String,
    val wind_direction_10m: String,
    val wind_speed_10m: String
)