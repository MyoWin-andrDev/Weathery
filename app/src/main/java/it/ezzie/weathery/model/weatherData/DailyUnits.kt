package it.ezzie.weathery.model.weatherData

data class DailyUnits(
    val apparent_temperature_max: String,
    val sunrise: String,
    val sunset: String,
    val sunshine_duration: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val uv_index_clear_sky_max: String,
    val uv_index_max: String,
    val weather_code: String,
    val wind_direction_10m_dominant: String,
    val wind_speed_10m_max: String
)