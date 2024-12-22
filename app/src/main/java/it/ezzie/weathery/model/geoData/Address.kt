package it.ezzie.weathery.model.geoData

data class Address(
    val state: String,
    val city: String,
    val country: String,
    val country_code: String,
    val junction: String,
    val postcode: String,
    val quarter: String,
    val road: String,
    val suburb: String
)