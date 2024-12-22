package it.ezzie.weathery.model.weatherData

data class Daily(
    val apparent_temperature_max: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val sunshine_duration: List<Double>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val uv_index_clear_sky_max: List<Double>,
    val uv_index_max: List<Double>,
    val weather_code: List<Int>,
    val wind_direction_10m_dominant: List<Int>,
    val wind_speed_10m_max: List<Double>
)