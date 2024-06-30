package com.example.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnits(
    @SerialName("interval")
    val interval: String,
    @SerialName("is_day")
    val isDay: String,
    @SerialName("temperature_2m")
    val temperature2m: String,
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("wind_direction_10m")
    val windDirection10m: String,
    @SerialName("wind_speed_10m")
    val windSpeed10m: String
)