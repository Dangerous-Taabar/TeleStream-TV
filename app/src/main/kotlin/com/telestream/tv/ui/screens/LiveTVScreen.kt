package com.telestream.tv.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LiveTVScreen(onBack: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = "Live TV Channels",
                style = MaterialTheme.typography.headlineMedium
            )
            
            // Search Bar Placeholder
            Surface(
                onClick = { /* Open keyboard */ },
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Continue Watching", style = MaterialTheme.typography.titleMedium)
        TvLazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(4) { index ->
                ChannelCard(name = "Recent $index")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "All Channels", style = MaterialTheme.typography.titleMedium)
        TvLazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) { index ->
                ChannelCard(name = "Channel $index")
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
fun ChannelCard(name: String) {
    Surface(
        onClick = { /* Open player */ },
        modifier = Modifier
            .width(160.dp)
            .height(90.dp),
        shape = ClickableSurfaceDefaults.shape(),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(text = name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
