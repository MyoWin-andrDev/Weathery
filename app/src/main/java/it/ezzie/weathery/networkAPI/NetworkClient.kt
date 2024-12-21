package it.ezzie.weathery.networkAPI

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import it.ezzie.weathery.model.CurrentWeather
import it.ezzie.weathery.model.CurrentWeatherUnits
import it.ezzie.weathery.model.Daily
import it.ezzie.weathery.model.DailyUnits
import it.ezzie.weathery.model.Hourly
import it.ezzie.weathery.model.HourlyUnits
import it.ezzie.weathery.model.WeatherData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


class NetworkClient{
    private val client = HttpClient {
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        install(Logging){
            level = LogLevel.BODY
        }
    }

    suspend fun getWeatherInfo(latitude : Double, longitude : Double) : WeatherData {
        val response : HttpResponse = client.get("https://api.open-meteo.com/v1/forecast?latitude=$latitude&longitude=$longitude&current=temperature_2m,relative_humidity_2m,is_day,rain,weather_code,wind_speed_10m&hourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max,uv_index_clear_sky_max&current_weather=true")
        val jsonResponse = response.body<JsonObject>()
        val currentWeather = jsonResponse["current_weather"]!!.jsonObject
        val currentWeatherUnits = jsonResponse["current_weather_units"]!!.jsonObject
        val daily = jsonResponse["daily"]!!.jsonObject
        val dailyUnits = jsonResponse["daily_units"]!!.jsonObject
        val elevation = jsonResponse["elevation"]!!.jsonPrimitive.double
        val generationtimeMs = jsonResponse["generationtime_ms"]!!.jsonPrimitive.double
        val hourly = jsonResponse["hourly"]!!.jsonObject
        val hourlyUnits = jsonResponse["hourly_units"]!!.jsonObject
        val latitude = jsonResponse["latitude"]!!.jsonPrimitive.double
        val longitude = jsonResponse["longitude"]!!.jsonPrimitive.double
        val timezone = jsonResponse["timezone"]!!.jsonPrimitive.content
        val timezoneAbbreviation = jsonResponse["timezone_abbreviation"]!!.jsonPrimitive.content
        val utcOffsetSeconds = jsonResponse["utc_offset_seconds"]!!.jsonPrimitive.int
        return WeatherData(
            CurrentWeather(
                interval = currentWeather["interval"]!!.jsonPrimitive.int,
                is_day = currentWeather["is_day"]!!.jsonPrimitive.int,
                temperature = currentWeather["temperature"]!!.jsonPrimitive.double,
                time = currentWeather["time"]!!.jsonPrimitive.content,
                weathercode = currentWeather["weathercode"]!!.jsonPrimitive.int,
                winddirection = currentWeather["winddirection"]!!.jsonPrimitive.int,
                windspeed = currentWeather["windspeed"]!!.jsonPrimitive.double
            ),
            CurrentWeatherUnits(
                interval = currentWeatherUnits["interval"]!!.jsonPrimitive.content,
                is_day = currentWeatherUnits["is_day"]!!.jsonPrimitive.content,
                temperature = currentWeatherUnits["temperature"]!!.jsonPrimitive.content,
                time = currentWeatherUnits["time"]!!.jsonPrimitive.content,
                weathercode = currentWeatherUnits["weathercode"]!!.jsonPrimitive.content,
                winddirection = currentWeatherUnits["winddirection"]!!.jsonPrimitive.content,
                windspeed = currentWeatherUnits["windspeed"]!!.jsonPrimitive.content
            ),
            Daily(
                apparent_temperature_max = daily["apparent_temperature_max"]!!.jsonArray.map{ it.jsonPrimitive.double},
                sunrise = daily["sunrise"]!!.jsonArray.map { it.jsonPrimitive.content },
                sunset = daily["sunset"]!!.jsonArray.map { it.jsonPrimitive.content },
                sunshine_duration = daily["sunshine_duration"]!!.jsonArray.map{ it.jsonPrimitive.double},
                temperature_2m_max = daily["temperature_2m_max"]!!.jsonArray.map { it.jsonPrimitive.double },
                temperature_2m_min = daily["temperature_2m_min"]!!.jsonArray.map { it.jsonPrimitive.double },
                time = daily["time"]!!.jsonArray.map { it.jsonPrimitive.content },
                uv_index_clear_sky_max = daily["uv_index_clear_sky_max"]!!.jsonArray.map{ it.jsonPrimitive.double},
                uv_index_max = daily["uv_index_max"]!!.jsonArray.map{ it.jsonPrimitive.double},
                weather_code = daily["weather_code"]!!.jsonArray.map{ it.jsonPrimitive.int},
                wind_direction_10m_dominant = daily["wind_direction_10m_dominant"]!!.jsonArray.map { it.jsonPrimitive.int },
                wind_speed_10m_max = daily["wind_speed_10m_max"]!!.jsonArray.map { it.jsonPrimitive.double }
            ),
            DailyUnits(
                apparent_temperature_max = dailyUnits["apparent_temperature_max"]!!.jsonPrimitive.content,
                sunrise = dailyUnits["sunrise"]!!.jsonPrimitive.content,
                sunset = dailyUnits["sunset"]!!.jsonPrimitive.content,
                sunshine_duration = dailyUnits["sunshine_duration"]!!.jsonPrimitive.content,
                temperature_2m_max = dailyUnits["temperature_2m_max"]!!.jsonPrimitive.content,
                temperature_2m_min = dailyUnits["temperature_2m_min"]!!.jsonPrimitive.content,
                time = dailyUnits["time"]!!.jsonPrimitive.content,
                uv_index_clear_sky_max = dailyUnits["uv_index_clear_sky_max"]!!.jsonPrimitive.content,
                uv_index_max = dailyUnits["uv_index_max"]!!.jsonPrimitive.content,
                weather_code = dailyUnits["weather_code"]!!.jsonPrimitive.content,
                wind_direction_10m_dominant = dailyUnits["wind_direction_10m_dominant"]!!.jsonPrimitive.content,
                wind_speed_10m_max = dailyUnits["wind_speed_10m_max"]!!.jsonPrimitive.content
            ),
            elevation = elevation,
            generationtime_ms = generationtimeMs,
            Hourly(
                pressure_msl= hourly["pressure_msl"]!!.jsonArray.map { it.jsonPrimitive.double },
                relative_humidity_2m = hourly["relative_humidity_2m"]!!.jsonArray.map { it.jsonPrimitive.int },
                temperature_2m = hourly["temperature_2m"]!!.jsonArray.map { it.jsonPrimitive.double },
                time = hourly["time"]!!.jsonArray.map { it.jsonPrimitive.content },
                visibility = hourly["visibility"]!!.jsonArray.map { it.jsonPrimitive.double },
                weather_code = hourly["weather_code"]!!.jsonArray.map{ it.jsonPrimitive.int}
            ),
            HourlyUnits(
                pressure_msl = hourlyUnits["pressure_msl"]!!.jsonPrimitive.content,
                relative_humidity_2m = hourlyUnits["relative_humidity_2m"]!!.jsonPrimitive.content,
                temperature_2m = hourlyUnits["temperature_2m"]!!.jsonPrimitive.content,
                time = hourlyUnits["time"]!!.jsonPrimitive.content,
                visibility = hourlyUnits["visibility"]!!.jsonPrimitive.content,
                weather_code = hourlyUnits["weather_code"]!!.jsonPrimitive.content
            ),
            latitude = latitude,
            longitude = longitude,
            timezone = timezone,
            timezone_abbreviation =  timezoneAbbreviation,
            utc_offset_seconds = utcOffsetSeconds
        )
    }
}