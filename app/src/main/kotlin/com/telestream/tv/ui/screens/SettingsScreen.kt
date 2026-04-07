package com.telestream.tv.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text(text = "App Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(text = "Configuration options will appear here.", style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(onClick = onBack) {
            Text("Back to Launcher")
        }
    }
}
