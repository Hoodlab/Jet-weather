package com.example.jetweatherapp.ui.daily

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherapp.domain.models.Daily
import com.example.jetweatherapp.domain.repository.WeatherRepository
import com.example.jetweatherapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    var dailyState by mutableStateOf(DailyState())
        private set

    init {
        viewModelScope.launch {
            repository.getWeatherData().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        dailyState = dailyState.copy(
                            isLoading = true
                        )
                    }

                    is Response.Success -> {
                        dailyState = dailyState.copy(
                            isLoading = false,
                            daily = response.data?.daily,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        dailyState = dailyState.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }
            }
        }
    }


}

data class DailyState(
    val daily: Daily? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)