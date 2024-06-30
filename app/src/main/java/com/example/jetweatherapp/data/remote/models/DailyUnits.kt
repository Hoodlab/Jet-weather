package com.example.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    @SerialName("sunrise")
    val sunrise: String,
    @SerialName("sunset")
    val sunset: String,
    @SerialName("temperature_2m_max")
    val temperature2mMax: String,
    @SerialName("temperature_2m_min")
    val temperature2mMin: String,
    @SerialName("time")
    val time: String,
    @SerialName("uv_index_max")
    val uvIndexMax: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("wind_direction_10m_dominant")
    val windDirection10mDominant: String,
    @SerialName("wind_speed_10m_max")
    val windSpeed10mMax: String
)