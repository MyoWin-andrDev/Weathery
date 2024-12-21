package it.ezzie.weathery.model

data class Hourly(
    val pressure_msl: List<Double>,
    val relative_humidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    val weather_code: List<Int>
)