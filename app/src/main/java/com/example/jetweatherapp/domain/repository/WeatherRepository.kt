package com.example.jetweatherapp.domain.repository

import com.example.jetweatherapp.domain.models.Weather
import com.example.jetweatherapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherData(): Flow<Response<Weather>>
}