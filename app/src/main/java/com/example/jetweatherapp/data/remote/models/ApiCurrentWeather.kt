package com.example.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCurrentWeather(
    @SerialName("interval")
    val interval: Int,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("temperature_2m")
    val temperature2m: Double,
    @SerialName("time")
    val time: Long,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("wind_direction_10m")
    val windDirection10m: Double,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double
)