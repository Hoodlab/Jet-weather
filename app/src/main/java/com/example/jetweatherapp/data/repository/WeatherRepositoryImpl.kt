package com.example.jetweatherapp.data.repository

import com.example.jetweatherapp.data.mapper_impl.ApiWeatherMapper
import com.example.jetweatherapp.data.remote.WeatherApi
import com.example.jetweatherapp.domain.models.Weather
import com.example.jetweatherapp.domain.repository.WeatherRepository
import com.example.jetweatherapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiWeatherMapper: ApiWeatherMapper
) : WeatherRepository {
    override fun getWeatherData(): Flow<Response<Weather>> = flow {
        emit(Response.Loading())
        val apiWeather = weatherApi.getWeatherData()
        val weather = apiWeatherMapper.mapToDomain(apiWeather)
        emit(Response.Success(weather))
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e.message.orEmpty()))
    }
}