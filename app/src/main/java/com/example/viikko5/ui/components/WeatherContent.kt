package com.example.viikko5.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import com.example.viikko5.data.model.WeatherResponse
import coil.compose.AsyncImage
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Arrangement

@Composable
fun WeatherContent (weather: WeatherResponse) {

    val condition = weather.weather.firstOrNull()
    val iconUrl = "https://openweathermap.org/img/wn/${condition?.icon}@4x.png"

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = "${weather.name}, ${weather.sys.country}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = iconUrl,
            contentDescription = "Sääkuvake",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = condition?.description ?: "",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(32.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                WeatherDetailRow("Tuntuu kuin", "${weather.main.feels_like.toInt()}°C")
                WeatherDetailRow("Min / Max", "${weather.main.tempMin.toInt()}°C / ${weather.main.tempMax.toInt()}°C")
                WeatherDetailRow("Kosteus", "${weather.main.humidity}%")
                WeatherDetailRow("Tuuli", "${weather.wind.speed} m/s")
                WeatherDetailRow("Paine", "${weather.main.pressure} hPa")
            }
        }
    }
}

@Composable
fun WeatherDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}