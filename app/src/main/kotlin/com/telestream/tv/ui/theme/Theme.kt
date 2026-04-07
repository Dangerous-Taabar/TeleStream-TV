package com.telestream.tv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
private val DarkColorScheme = darkColorScheme(
    primary = NeonBlue,
    secondary = ElectricViolet,
    tertiary = VividMagenta,
    background = DeepIndigo,
    surface = MidnightBlue,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TeleStreamTVTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme // Always dark for TV apps usually

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
