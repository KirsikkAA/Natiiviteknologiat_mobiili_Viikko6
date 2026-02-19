package com.example.viikko5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.viikko5.ui.theme.Viikko5Theme
import com.example.viikko5.ui.WeatherScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viikko5.ViewModel.WeatherViewModel
import com.example.viikko5.data.local.AppDatabase
import com.example.viikko5.data.repository.WeatherRepository

class MainActivity : ComponentActivity() {

   private val database by lazy {
       AppDatabase.getDatabase(applicationContext)
   }
    private val repository by lazy {
        WeatherRepository(database.weatherDao())
    }

    private val viewModel: WeatherViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return WeatherViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen(viewModel = viewModel)
                }
            }
        }
    }
}
