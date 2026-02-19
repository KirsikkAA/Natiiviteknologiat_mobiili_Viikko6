package com.example.viikko5.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "weather")
data class WeatherEntity(
    @PrimaryKey
    val city: String,
    val temperature: Double,
    val description: String,
    val icon: String,
    val timestamp: Long
)
fun WeatherEntity.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        weather = listOf(
            Weather(
                id = 0,
                main = "",
                description = description,
                icon = icon
            )
        ),
        main = Main(
            temp = temperature,
            feels_like = temperature,
            tempMin = temperature,
            tempMax = temperature,
            pressure = 0,
            humidity = 0
        ),
        wind = Wind(0.0, 0),
        name = city,
        sys = Sys("", 0, 0)
    )
}