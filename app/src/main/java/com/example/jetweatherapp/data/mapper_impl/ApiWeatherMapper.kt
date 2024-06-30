package com.example.jetweatherapp.data.mapper_impl

import com.example.jetweatherapp.data.mappers.ApiMapper
import com.example.jetweatherapp.data.remote.models.ApiCurrentWeather
import com.example.jetweatherapp.data.remote.models.ApiDailyWeather
import com.example.jetweatherapp.data.remote.models.ApiHourlyWeather
import com.example.jetweatherapp.data.remote.models.ApiWeather
import com.example.jetweatherapp.di.ApiCurrentWeatherMapperAnnotation
import com.example.jetweatherapp.di.ApiDailyMapperAnnotation
import com.example.jetweatherapp.di.ApiHourlyWeatherMapperAnnotation
import com.example.jetweatherapp.domain.models.CurrentWeather
import com.example.jetweatherapp.domain.models.Daily
import com.example.jetweatherapp.domain.models.Hourly
import com.example.jetweatherapp.domain.models.Weather
import javax.inject.Inject

class ApiWeatherMapper @Inject constructor(
    @ApiDailyMapperAnnotation private val apiDailyMapper: ApiMapper<Daily, ApiDailyWeather>,
    @ApiCurrentWeatherMapperAnnotation private val apiCurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather>,
    @ApiHourlyWeatherMapperAnnotation private val apiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather>,
) : ApiMapper<Weather, ApiWeather> {
    override fun mapToDomain(apiEntity: ApiWeather): Weather {
        return Weather(
            currentWeather = apiCurrentWeatherMapper.mapToDomain(apiEntity.current),
            daily = apiDailyMapper.mapToDomain(apiEntity.daily),
            hourly = apiHourlyMapper.mapToDomain(apiEntity.hourly)
        )
    }
}