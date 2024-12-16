package it.ezzie.weathery.dataReturn

import it.ezzie.weathery.R

class WeatherCondition {
    fun codeToCondition(weatherCode: Int): String {
        return when (weatherCode) {
            0 -> "Clear Sky"
            1 -> "Mainly Clear"
            2 -> "Partly Cloudy"
            3 -> "Overcast"
            45 -> "Fog"
            48 -> "Fog Depositing Rime"
            51 -> "Light Drizzle"
            53 -> "Moderate Drizzle"
            55 -> "Dense Drizzle"
            56 -> "Light Freezing Drizzle"
            57 -> "Dense Freezing Drizzle"
            61 -> "Slight Rain"
            63 -> "Moderate Rain"
            65 -> "Heavy Rain"
            66 -> "Light Freezing Rain"
            67 -> "Heavy Freezing Rain"
            71 -> "Slight Snow Fall"
            73 -> "Moderate Snow Fall"
            75 -> "Heavy Snow Fall"
            77 -> "Snow Grains"
            80 -> "Slight Rain Showers"
            81 -> "Moderate Rain Showers"
            82 -> "Violent Rain Showers"
            85 -> "Slight Snow Showers"
            86 -> "Heavy Snow Showers"
            95 -> "Thunderstorm: Slight or Moderate"
            96 -> "Thunderstorm with Slight Hail"
            99 -> "Thunderstorm with Heavy Hail"
            else -> "Unknown Weather Code"
        }
    }

    fun codeToIcon(weatherCode: Int) : Int{
        return when (weatherCode){
            0 -> R.drawable.sunny
            1 -> R.drawable.sunny
            2 -> R.drawable.cloudy
            3 -> R.drawable.cloud
            45 -> R.drawable.cloudy_1
            48 -> R.drawable.cloudy_1
            51 -> R.drawable.rainy
            53 -> R.drawable.rainy
            55 -> R.drawable.rainy
            56 -> R.drawable.rainy
            57 -> R.drawable.rainy
            61 -> R.drawable.rainy
            63 -> R.drawable.storm_1
            65 -> R.drawable.storm_1
            66 -> R.drawable.storm
            67 -> R.drawable.storm
            71 -> R.drawable.windy
            73 -> R.drawable.snowy
            75 -> R.drawable.snowy
            77 -> R.drawable.snowy
            80 -> R.drawable.rainy
            81 -> R.drawable.storm_1
            82 -> R.drawable.storm
            85 -> R.drawable.windy
            86 -> R.drawable.snowy
            95 -> R.drawable.storm_1
            96 -> R.drawable.storm
            99 -> R.drawable.storm
            else -> R.drawable.cloudy
        }
    }

}