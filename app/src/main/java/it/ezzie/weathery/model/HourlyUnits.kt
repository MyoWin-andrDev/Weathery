package it.ezzie.weathery.model

data class HourlyUnits(
    val pressure_msl: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val visibility: String,
    val weather_code: String
)