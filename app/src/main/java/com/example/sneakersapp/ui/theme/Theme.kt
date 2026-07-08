package com.example.sneakersapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = DarkSurface,
    secondary = GreyText,
    onSecondary = DarkSurface,
    background = White,
    onBackground = DarkText,
    surface = CardWhite,
    onSurface = DarkText,
    surfaceVariant = OffWhite,
    onSurfaceVariant = GreyText,
    outline = LightBorder,
    onPrimaryContainer = LimeGreen

)
@Composable
fun SneakersAppTheme(
  //  darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}