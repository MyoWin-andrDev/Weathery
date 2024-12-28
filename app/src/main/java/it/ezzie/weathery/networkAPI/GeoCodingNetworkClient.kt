package it.ezzie.weathery.networkAPI

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import it.ezzie.weathery.model.geoData.Address
import it.ezzie.weathery.model.geoData.GeoData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class GeoCodingNetworkClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }
    suspend fun getGeoData(latitude : Double, longitude : Double) : GeoData{
        val response : HttpResponse = client.get("https://geocode.maps.co/reverse?lat=$latitude&lon=$longitude&api_key=6767f8d9aab2b771527120pied956bd")
        val jsonResponse = response.body<JsonObject>()
        val address = jsonResponse["address"]!!.jsonObject
//        val boundingbox = jsonResponse["boundingbox"]!!.jsonArray.map { it.jsonPrimitive.content }
//        val display_name = jsonResponse["display_name"]!!.jsonPrimitive.content
//        val lat = jsonResponse["lat"]!!.jsonPrimitive.content
//        val licence = jsonResponse["licence"]!!.jsonPrimitive.content
//        val lon = jsonResponse["lon"]!!.jsonPrimitive.content
//        val osm_id = jsonResponse["osm_id"]!!.jsonPrimitive.int
//        val osm_type = jsonResponse["osm_type"]!!.jsonPrimitive.content
//        val place_id = jsonResponse["place_id"]!!.jsonPrimitive.int
        return GeoData(
            Address(
//                quarter = address["quarter"]?.jsonPrimitive?.content,
//                state = address["ISO3166-2-lvl4"]?.jsonPrimitive?.content,
                city = address["city"]!!.jsonPrimitive.content,
//                country = address["country"]!!.jsonPrimitive.content,
//                country_code = address["country_code"]!!.jsonPrimitive.content,
//                junction = address["country_code"]?.jsonPrimitive?.content,
//                postcode = address["postcode"]!!.jsonPrimitive.content,
//                suburb = address["suburb"]?.jsonPrimitive?.content
            )
//            boundingbox = boundingbox,
//            display_name = display_name,
//            lat = lat,
//            licence = licence,
//            lon = lon,
//            osm_id = osm_id,
//            osm_type = osm_type,
//            place_id = place_id
       )
    }
}