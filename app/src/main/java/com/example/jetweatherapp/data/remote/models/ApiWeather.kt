package com.example.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeather(
    @SerialName("current")
    val current: ApiCurrentWeather,
    @SerialName("current_units")
    val currentUnits: CurrentUnits,
    @SerialName("daily")
    val daily: ApiDailyWeather,
    @SerialName("daily_units")
    val dailyUnits: DailyUnits,
    @SerialName("elevation")
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationtimeMs: Double,
    @SerialName("hourly")
    val hourly: ApiHourlyWeather,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)