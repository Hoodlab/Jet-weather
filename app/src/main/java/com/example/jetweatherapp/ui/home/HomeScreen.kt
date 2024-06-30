package com.example.jetweatherapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetweatherapp.domain.models.CurrentWeather
import com.example.jetweatherapp.domain.models.Daily
import com.example.jetweatherapp.domain.models.Hourly
import com.example.jetweatherapp.utils.Util
import java.util.Date

const val degreeTxt = "\u0000"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = homeViewModel.homeState
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (homeState.isLoading) {
            true -> {
                CircularProgressIndicator()
            }

            else -> {
                homeState.weather?.let {
                    CurrentWeatherItem(
                        currentWeather = it.currentWeather,
                        modifier = Modifier.align(Alignment.TopCenter),
                    )

                    HourlyWeatherItem(
                        hourly = it.hourly,
                        modifier = Modifier.align(Alignment.BottomCenter),
                    )

                }
                homeState.dailyWeatherInfo?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SunSetWeatherItem(weatherInfo = it)
                        UvIndexWeatherItem(weatherInfo = it)
                    }
                }
            }
        }
    }

}


@Composable
fun CurrentWeatherItem(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = currentWeather.weatherStatus.icon),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = currentWeather.temperature.toString() + degreeTxt,
            style = MaterialTheme.typography.displayMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Weather Status:${currentWeather.weatherStatus.info}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Wind speed:${currentWeather.windSpeed} Km/h ${currentWeather.windDirection} ",
            style = MaterialTheme.typography.bodyMedium,
        )

    }

}

@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourly: Hourly,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = Util.formatNormalDate("MMMM,dd", Date().time),
                style = MaterialTheme.typography.bodyMedium,
            )

        }
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        LazyRow(
            modifier = Modifier.padding(16.dp)
        ) {
            items(hourly.weatherInfo) { infoItem ->
                HourlyWeatherInfoItem(infoItem = infoItem)
            }
        }

    }


}

@Composable
fun HourlyWeatherInfoItem(
    modifier: Modifier = Modifier,
    infoItem: Hourly.HourlyInfoItem,
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${infoItem.temperature} $degreeTxt",
            style = MaterialTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(id = infoItem.weatherStatus.icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = infoItem.time,
            style = MaterialTheme.typography.bodySmall,
        )

    }

}

@Composable
fun SunSetWeatherItem(modifier: Modifier = Modifier, weatherInfo: Daily.WeatherInfo) {
    Card(modifier = Modifier.padding(horizontal = 8.dp)) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sunrise",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = weatherInfo.sunrise,
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = "Sunset ${weatherInfo.sunset}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}


@Composable
fun UvIndexWeatherItem(modifier: Modifier = Modifier, weatherInfo: Daily.WeatherInfo) {
    Card(modifier = Modifier.padding(horizontal = 8.dp)) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "UV INDEX",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = weatherInfo.uvIndex.toString(),
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = "Status ${weatherInfo.weatherStatus.info}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}












