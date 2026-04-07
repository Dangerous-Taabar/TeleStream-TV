package com.telestream.tv.ui.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import com.telestream.tv.ui.theme.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LiveTVScreen(onBack: () -> Unit) {
    var selectedCategory by remember { mutableStateOf("All Channels") }
    val categories = listOf("Favorites", "All Channels", "News", "Movies", "Sports", "Music")

    Row(modifier = Modifier.fillMaxSize().background(DeepIndigo)) {
        // Tivimate-Style Left Sidebar
        Column(
            modifier = Modifier
                .width(240.dp)
                .fillMaxHeight()
                .background(MidnightBlue.copy(alpha = 0.5f))
                .padding(16.dp)
        ) {
            Text(
                text = "TeleStream TV",
                style = MaterialTheme.typography.titleLarge,
                color = NeonBlue,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            categories.forEach { category ->
                CategoryItem(
                    name = category,
                    isSelected = selectedCategory == category,
                    onSelect = { selectedCategory = category }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Surface(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.medium),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = Color.Transparent,
                    focusedContainerColor = NeonBlue.copy(alpha = 0.2f)
                )
            ) {
                Text(
                    text = "Go Back",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }

        // Main Channel Grid Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text(
                text = selectedCategory,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            // Grid of Channels
            androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid(
                columns = androidx.tv.foundation.lazy.grid.TvGridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(20) { index ->
                    ChannelCard(name = "Channel ${index + 1}")
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CategoryItem(name: String, isSelected: Boolean, onSelect: () -> Unit) {
    var isFocused by remember { mutableStateOf(false) }
    
    Surface(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = 4.dp)
            .onFocusChanged { isFocused = it.isFocused },
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.small),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = if (isSelected) NeonBlue.copy(alpha = 0.3f) else Color.Transparent,
            focusedContainerColor = NeonBlue.copy(alpha = 0.2f)
        )
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleMedium,
            color = if (isSelected || isFocused) NeonBlue else Color.White.copy(alpha = 0.7f)
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ChannelCard(name: String) {
    var isFocused by remember { mutableStateOf(false) }

    Surface(
        onClick = { /* Open player */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .onFocusChanged { isFocused = it.isFocused }
            .scale(if (isFocused) 1.05f else 1.00f),
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.medium),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = GlassWhite,
            focusedContainerColor = NeonBlue.copy(alpha = 0.2f)
        ),
        border = ClickableSurfaceDefaults.border(
            border = Border(androidx.compose.foundation.BorderStroke(2.dp, if (isFocused) NeonBlue else Color.Transparent))
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(text = name, style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}
