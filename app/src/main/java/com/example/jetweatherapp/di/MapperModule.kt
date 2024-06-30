package com.example.jetweatherapp.di

import com.example.jetweatherapp.data.mapper_impl.ApiDailyMapper
import com.example.jetweatherapp.data.mapper_impl.ApiHourlyMapper
import com.example.jetweatherapp.data.mapper_impl.ApiWeatherMapper
import com.example.jetweatherapp.data.mapper_impl.CurrentWeatherMapper
import com.example.jetweatherapp.data.mappers.ApiMapper
import com.example.jetweatherapp.data.remote.models.ApiCurrentWeather
import com.example.jetweatherapp.data.remote.models.ApiDailyWeather
import com.example.jetweatherapp.data.remote.models.ApiHourlyWeather
import com.example.jetweatherapp.data.remote.models.ApiWeather
import com.example.jetweatherapp.domain.models.CurrentWeather
import com.example.jetweatherapp.domain.models.Daily
import com.example.jetweatherapp.domain.models.Hourly
import com.example.jetweatherapp.domain.models.Weather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @ApiDailyMapperAnnotation
    @Provides
    fun provideDailyApiMapper(): ApiMapper<Daily, ApiDailyWeather> {
        return ApiDailyMapper()
    }

    @ApiCurrentWeatherMapperAnnotation
    @Provides
    fun provideCurrentWeatherMapper(): ApiMapper<CurrentWeather, ApiCurrentWeather> {
        return CurrentWeatherMapper()
    }

    @ApiHourlyWeatherMapperAnnotation
    @Provides
    fun provideHourlyMapper(): ApiMapper<Hourly, ApiHourlyWeather> {
        return ApiHourlyMapper()
    }

    @ApiWeatherMapperAnnotation
    @Provides
    fun provideApiWeatherMapper(
        apiDailyMapper: ApiMapper<Daily, ApiDailyWeather>,
        apiCurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather>,
        apiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather>,
    ): ApiMapper<Weather, ApiWeather> {
        return ApiWeatherMapper(
            apiDailyMapper,
            apiCurrentWeatherMapper,
            apiHourlyMapper,
        )
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiDailyMapperAnnotation


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWeatherMapperAnnotation


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiCurrentWeatherMapperAnnotation


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiHourlyWeatherMapperAnnotation















