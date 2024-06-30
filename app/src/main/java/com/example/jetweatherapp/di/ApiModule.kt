package com.example.jetweatherapp.di

import com.example.jetweatherapp.data.remote.WeatherApi
import com.example.jetweatherapp.utils.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): WeatherApi {
        return builder
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
    }

}