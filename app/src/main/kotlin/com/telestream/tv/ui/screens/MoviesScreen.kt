package com.telestream.tv.ui.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import com.telestream.tv.ui.theme.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MoviesScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(DeepIndigo).padding(48.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = "Cinema Lounge",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
            
            // Search Bar Placeholder
            Surface(
                onClick = { /* Search */ },
                modifier = Modifier.width(300.dp).height(48.dp),
                shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.extraLarge),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = GlassWhite,
                    focusedContainerColor = NeonBlue.copy(alpha = 0.2f)
                ),
                border = ClickableSurfaceDefaults.border(
                    border = Border(androidx.compose.foundation.BorderStroke(2.dp, GlassBorder))
                )
            ) {
                Text(
                    text = "Search Movies...",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        TvLazyVerticalGrid(
            columns = TvGridCells.Fixed(5),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(15) { index ->
                MovieCard(title = "Cinema Title ${index + 1}")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Surface(
            onClick = onBack,
            modifier = Modifier.width(180.dp).height(48.dp),
            shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.medium),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color.Transparent,
                focusedContainerColor = NeonBlue.copy(alpha = 0.2f)
            )
        ) {
            Text(
                text = "Back to Home",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieCard(title: String) {
    var isFocused by remember { mutableStateOf(false) }

    Surface(
        onClick = { /* Open details */ },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2/3f)
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
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
        ) {
            // Gradient Overlay for Text
            Box(modifier = Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Brush.verticalGradient(
                listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
            )))

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
