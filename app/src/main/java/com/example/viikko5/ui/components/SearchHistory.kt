package com.example.viikko5.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko5.data.model.WeatherEntity

@Composable
fun SearchHistory(
    history: List<WeatherEntity>,
    onItemClick: (String) -> Unit
){
    if(history.isNotEmpty()){
        Text(
            text="Hakuhistoria",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp)
        ) {
            items(history) { item ->
                Text(
                    text = item.city,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onItemClick(item.city) }
                )
                Divider()
            }
        }
    }
}