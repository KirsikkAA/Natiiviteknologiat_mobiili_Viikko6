package com.example.viikko5.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.viikko5.ViewModel.WeatherViewModel
import com.example.viikko5.ui.components.SearchBar
import com.example.viikko5.ui.components.WeatherContent
import com.example.viikko5.data.util.Result
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val weatherState by viewModel.weatherState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.onSearchQueryChange(it) },
            onSearch = { viewModel.searchWeather() }
        )

        when (val state = weatherState) {
            Result.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text="Hae kaupunki")
                }
            }

            is Result.Success -> {
                WeatherContent(weather = state.data)
            }
            is Result.Error -> {
                ErrorScreen(
                    message = state.exception.message ?: "Virhe",
                    onRetry = { viewModel.searchWeather() }
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Yritä uudelleen")
        }
    }
}