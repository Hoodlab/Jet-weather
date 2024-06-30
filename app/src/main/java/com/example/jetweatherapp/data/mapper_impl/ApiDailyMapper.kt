package com.example.jetweatherapp.data.mapper_impl

import com.example.jetweatherapp.data.mappers.ApiMapper
import com.example.jetweatherapp.data.remote.models.ApiDailyWeather
import com.example.jetweatherapp.domain.models.Daily
import com.example.jetweatherapp.utils.Util
import com.example.jetweatherapp.utils.WeatherInfoItem

class ApiDailyMapper : ApiMapper<Daily, ApiDailyWeather> {
    override fun mapToDomain(apiEntity: ApiDailyWeather): Daily {
        return Daily(
            temperatureMax = apiEntity.temperature2mMax,
            temperatureMin = apiEntity.temperature2mMin,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode),
            windDirection = parseWeatherDirection(apiEntity.windDirection10mDominant),
            sunset = apiEntity.sunset.map { Util.formatUnixDate("HH:mm", it.toLong()) },
            sunrise = apiEntity.sunrise.map { Util.formatUnixDate("HH:mm", it.toLong()) },
            uvIndex = apiEntity.uvIndexMax,
            windSpeed = apiEntity.windSpeed10mMax
        )
    }

    private fun parseTime(time: List<Long>): List<String> {
        return time.map { Util.formatUnixDate("E", it) }
    }

    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
        return code.map {
            Util.getWeatherInfo(it)
        }
    }

    private fun parseWeatherDirection(windDirections: List<Double>): List<String> {
        return windDirections.map {
            Util.getWindDirection(it)
        }
    }

}