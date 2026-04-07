package com.telestream.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.*
import com.telestream.tv.ui.theme.TeleStreamTVTheme
import com.telestream.tv.ui.screens.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeleStreamTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = SurfaceDefaults.shape(),
                    colors = SurfaceDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedSection by remember { mutableStateOf<String?>(null) }
    
    if (selectedSection == null) {
        // Hero Launcher Home Screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to TeleStream TV",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            TvLazyRow(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    HeroCard(
                        title = "Live TV",
                        subtitle = "IPTV, XTREAM, STALKER",
                        imageRes = "livetv_hero_card",
                        onClick = { selectedSection = "LIVE_TV" }
                    )
                }
                item {
                    HeroCard(
                        title = "Movies & VOD",
                        subtitle = "CLOUDSTREAM & EXTENSIONS",
                        imageRes = "vod_hero_card",
                        onClick = { selectedSection = "VOD" }
                    )
                }
                item {
                    HeroCard(
                        title = "Telegram TV",
                        subtitle = "CHANNELS & MEDIA",
                        imageRes = "telegram_hero_card",
                        onClick = { selectedSection = "TELEGRAM" }
                    )
                }
            }
        }
    } else {
        // Handle Section Content
        Box(modifier = Modifier.fillMaxSize()) {
            when (selectedSection) {
                "LIVE_TV" -> LiveTVScreen(onBack = { selectedSection = null })
                "VOD" -> MoviesScreen(onBack = { selectedSection = null })
                "TELEGRAM" -> TelegramScreen(onBack = { selectedSection = null })
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HeroCard(title: String, subtitle: String, imageRes: String, onClick: () -> Unit) {
    var isFocused by remember { mutableStateOf(false) }
    val scale = if (isFocused) 1.1f else 1.0f

    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(360.dp)
            .height(200.dp)
            .onFocusChanged { isFocused = it.isFocused }
            .scale(scale),
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.medium),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
