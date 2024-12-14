package it.ezzie.weathery.model

data class DailyUnits(
    val daylight_duration: String,
    val sunrise: String,
    val sunset: String,
    val sunshine_duration: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val uv_index_clear_sky_max: String,
    val uv_index_max: String,
    val weather_code: String
)