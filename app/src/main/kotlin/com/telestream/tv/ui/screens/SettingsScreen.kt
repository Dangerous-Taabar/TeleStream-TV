package com.telestream.tv.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "App Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            item {
                SettingsItem(title = "Telegram API ID", value = "35419333")
            }
            item {
                SettingsItem(title = "Telegram API Hash", value = "8816a4fc88cc...")
            }
            item {
                SettingsItem(title = "Add M3U Playlist", value = "No active playlist")
            }
            item {
                SettingsItem(title = "Xtream Codes Portal", value = "Not configured")
            }
            item {
                SettingsItem(title = "Stalker Portal", value = "Not configured")
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsItem(title: String, value: String) {
    Surface(
        onClick = { /* Action */ },
        modifier = Modifier.fillMaxWidth().height(64.dp),
        shape = ClickableSurfaceDefaults.shape(),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = value, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
