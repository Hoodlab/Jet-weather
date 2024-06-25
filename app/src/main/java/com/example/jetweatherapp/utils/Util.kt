package com.example.jetweatherapp.utils

import androidx.annotation.DrawableRes
import com.example.jetweatherapp.R
import java.text.SimpleDateFormat
import java.util.*

object Util {
    private val DIRECTIONS = listOf(
        "North",
        "North East",
        "East",
        "South East",
        "South",
        "South West",
        "West",
        "North West"
    )


    fun formatUnixDate(pattern: String, time: Long): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(time * 1000)
    }

    fun formatNormalDate(pattern: String, time: Long): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(time)
    }

    fun getWindDirection(windDirection: Double): String {
        return DIRECTIONS[(windDirection % 360 / 45 % 8).toInt()]
    }

    fun getWeatherInfo(code: Int): WeatherInfoItem {
        return when (code) {
            0 -> WeatherInfoItem("Clear sky", R.drawable.clear_sky)
            1 -> WeatherInfoItem("Mainly clear", R.drawable.mainly_clear)
            2 -> WeatherInfoItem("partly cloudy", R.drawable.mainly_clear)
            3 -> WeatherInfoItem("overcast", R.drawable.over_cast)
            45, 48 -> WeatherInfoItem("Fog", R.drawable.fog)
            51, 53, 55,
            -> WeatherInfoItem("Drizzle", R.drawable.drizzle)

            56, 57 -> WeatherInfoItem("Freezing Drizzle", R.drawable.freezing_drizzle)
            61,
            -> WeatherInfoItem("Rain: Slight", R.drawable.rain_slight)

            63 -> WeatherInfoItem("Rain: Moderate", R.drawable.rain_heavy)
            65 -> WeatherInfoItem("Rain: Heavy", R.drawable.rain_heavy)
            66, 67 -> WeatherInfoItem("Freezing Rain", R.drawable.freezing_rain)
            71 -> WeatherInfoItem("Snow fall: Slight", R.drawable.snow_fall_slight)
            73 -> WeatherInfoItem("Snow fall: moderate", R.drawable.snow_fall_slight)
            75 -> WeatherInfoItem("Snow fall: Heavy", R.drawable.snow_fall)
            77 -> WeatherInfoItem("Snow grains", R.drawable.snow_fall)
            80, 81, 82 -> WeatherInfoItem("Rain showers: Slight", R.drawable.rain_slight)
            85, 86 -> WeatherInfoItem("Snow showers slight", R.drawable.snow_fall_slight)
            95, 96, 99 -> WeatherInfoItem("Thunderstorm: Slight", R.drawable.thunder_storm)
            else -> WeatherInfoItem("Unknown", R.drawable.clear_sky)
        }
    }

    fun isTodayDate(day: String): Boolean {
        val todayDate = formatNormalDate("E", Date().time)
        return todayDate.lowercase() == day.lowercase()
    }

}

data class WeatherInfoItem(
    val info: String,
    @DrawableRes val icon: Int
)