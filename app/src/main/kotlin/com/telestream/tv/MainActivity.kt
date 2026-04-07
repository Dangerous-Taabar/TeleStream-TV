package com.telestream.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.*
import coil.compose.AsyncImage
import com.telestream.tv.ui.theme.*
import com.telestream.tv.ui.screens.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeleStreamTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = androidx.compose.ui.graphics.RectangleShape,
                    colors = SurfaceDefaults.colors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(HomeGradient))) {
                        MainScreen()
                    }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "TeleStream TV",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Premium IPTV & Media Hub",
                style = MaterialTheme.typography.titleMedium,
                color = NeonBlue,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            TvLazyRow(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    HeroCard(
                        title = "Live Channels",
                        subtitle = "Global IPTV & Stalker",
                        imageRes = R.drawable.livetv_hero_card,
                        onClick = { selectedSection = "LIVE_TV" }
                    )
                }
                item {
                    HeroCard(
                        title = "Movies & Series",
                        subtitle = "Cinema Lounge Experience",
                        imageRes = R.drawable.vod_hero_card,
                        onClick = { selectedSection = "VOD" }
                    )
                }
                item {
                    HeroCard(
                        title = "Telegram TV",
                        subtitle = "Cloud Media Hub",
                        imageRes = R.drawable.telegram_hero_card,
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
fun HeroCard(title: String, subtitle: String, imageRes: Int, onClick: () -> Unit) {
    var isFocused by remember { mutableStateOf(false) }
    val scale = if (isFocused) 1.05f else 1.0f

    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(360.dp)
            .height(210.dp)
            .onFocusChanged { isFocused = it.isFocused }
            .scale(scale),
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.large),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = if (isFocused) NeonBlue.copy(alpha = 0.2f) else GlassWhite,
            focusedContainerColor = NeonBlue.copy(alpha = 0.2f)
        ),
        border = ClickableSurfaceDefaults.border(
            border = Border(androidx.compose.foundation.BorderStroke(2.dp, if (isFocused) NeonBlue else GlassBorder))
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageRes,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().alpha(0.7f),
                contentScale = ContentScale.Crop
            )
                modifier = Modifier.fillMaxSize().alpha(0.7f),
                contentScale = ContentScale.Crop
            )
            
            Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(
                listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f))
            )))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall, color = Color.White)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = NeonBlue)
            }
        }
    }
}
