package com.xrcaption.quest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF121212),
    surface = CaptionSurface,
    onBackground = CaptionWhite,
    onSurface = CaptionWhite,
    primary = AccentBlue,
    onPrimary = Color.Black,
    secondary = AccentGreen,
    error = ErrorRed,
    onError = Color.White
)

@Composable
fun XRCaptionTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = CaptionTypography,
        content = content
    )
}
