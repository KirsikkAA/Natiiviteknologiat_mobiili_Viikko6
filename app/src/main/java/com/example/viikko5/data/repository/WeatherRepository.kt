package com.example.viikko5.data.repository

import com.example.viikko5.data.model.WeatherResponse
import com.example.viikko5.BuildConfig
import java.io.IOException
import retrofit2.HttpException
import com.example.viikko5.data.remote.RetrofitInstance
import com.example.viikko5.data.util.Result
import com.example.viikko5.data.model.*
import com.example.viikko5.data.local.WeatherDao
import com.example.viikko5.data.model.WeatherEntity
import kotlinx.coroutines.flow.Flow

class WeatherRepository (
    private val weatherDao: WeatherDao
){
    private val apiService = RetrofitInstance.weatherApi
    private val apiKey = BuildConfig.OPENWEATHER_API_KEY

    private val cacheDuration = 30 * 60 * 1000L //30 min

    suspend fun getWeather(city: String): Result<WeatherResponse> {
        val cached = weatherDao.getWeather(city)
        val now = System.currentTimeMillis()

        if (cached != null && now - cached.timestamp < cacheDuration) {
            return Result.Success(cached.toWeatherResponse())
        }

        return try {
            val response = apiService.getWeather(city, apiKey)

            weatherDao.insertWeather(
                WeatherEntity(
                    city = response.name,
                    temperature = response.main.temp,
                    description = response.weather.firstOrNull()?.description ?: "",
                    icon = response.weather.firstOrNull()?.icon ?: "",
                    timestamp = now
                )
            )

            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(Exception("Verkkovirhe: ${e.message}"))
        } catch (e: HttpException) {
            Result.Error(Exception("Palvelinvirhe: ${e.code()}"))
        } catch (e: Exception) {
            Result.Error(Exception("Tuntematon virhe: ${e.message}"))
        }
    }
    fun getAllWeather(): Flow<List<WeatherEntity>> {
        return weatherDao.getAllWeather()
    }
}
