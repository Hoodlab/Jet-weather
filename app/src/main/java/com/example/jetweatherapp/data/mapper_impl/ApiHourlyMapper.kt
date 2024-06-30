package com.example.jetweatherapp.data.mapper_impl

import com.example.jetweatherapp.data.mappers.ApiMapper
import com.example.jetweatherapp.data.remote.models.ApiHourlyWeather
import com.example.jetweatherapp.domain.models.Hourly
import com.example.jetweatherapp.utils.Util
import com.example.jetweatherapp.utils.WeatherInfoItem

class ApiHourlyMapper : ApiMapper<Hourly, ApiHourlyWeather> {
    override fun mapToDomain(apiEntity: ApiHourlyWeather): Hourly {
        return Hourly(
            temperature = apiEntity.temperature2m,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode)
        )
    }

    private fun parseTime(time: List<Long>): List<String> {
        return time.map {
            Util.formatUnixDate("HH:mm", it)
        }
    }

    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
        return code.map {
            Util.getWeatherInfo(it)
        }
    }


}