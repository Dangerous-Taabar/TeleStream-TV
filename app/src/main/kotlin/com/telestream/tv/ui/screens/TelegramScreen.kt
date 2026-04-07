package com.telestream.tv.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TelegramScreen(onBack: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = "Telegram TV Universe",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Surface(
                onClick = { /* Search */ },
                modifier = Modifier.width(300.dp).height(40.dp),
                shape = ClickableSurfaceDefaults.shape(),
                colors = ClickableSurfaceDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text(
                    text = if (searchQuery.isEmpty()) "Search Channels..." else searchQuery,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TvLazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(5) { channelIndex ->
                Text(text = "Channel Group $channelIndex", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                TvLazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(10) { videoIndex ->
                        TelegramVideoCard(title = "Video $videoIndex")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Button(onClick = onBack) {
            Text("Back to Launcher")
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TelegramVideoCard(title: String) {
    Surface(
        onClick = { /* Open player from Telegram source */ },
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        shape = ClickableSurfaceDefaults.shape(),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
