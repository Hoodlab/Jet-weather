package com.example.jetweatherapp.di

import com.example.jetweatherapp.data.repository.WeatherRepositoryImpl
import com.example.jetweatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl):WeatherRepository
}