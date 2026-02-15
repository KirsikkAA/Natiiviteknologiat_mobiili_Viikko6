package com.example.viikko5.data.repository

import com.example.viikko5.data.model.WeatherResponse
import com.example.viikko5.BuildConfig
import java.io.IOException
import retrofit2.HttpException
import com.example.viikko5.data.remote.RetrofitInstance
import com.example.viikko5.data.util.Result

class WeatherRepository {
    private val apiService = RetrofitInstance.weatherApi
    private val apiKey = BuildConfig.OPENWEATHER_API_KEY

    suspend fun  getWeather(city: String): Result<WeatherResponse> {
        return try {
            val response = apiService.getWeather(city, apiKey)
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(Exception("Verkkovirhe: ${e.message}"))
        } catch (e: HttpException) {
            Result.Error(Exception("Palvelinvirhe: ${e.code()}"))
        } catch (e: Exception) {
            Result.Error(Exception("Tuntematon virhe: ${e.message}"))
        }
    }
}