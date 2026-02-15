package com.example.viikko5.ViewModel

import com.example.viikko5.data.model.WeatherResponse
import com.example.viikko5.data.repository.WeatherRepository
import com.example.viikko5.data.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel (
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _weatherState = MutableStateFlow<Result<WeatherResponse>>(Result.Loading)
    val weatherState: StateFlow<Result<WeatherResponse>> = _weatherState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun searchWeather() {
        val city = _searchQuery.value
        if (city.isBlank()) return

        viewModelScope.launch {
            _weatherState.value = Result.Loading
            _weatherState.value = repository.getWeather(city)
        }
    }
}
