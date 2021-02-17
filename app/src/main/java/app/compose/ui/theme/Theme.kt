package app.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = purple,
    primaryVariant = darkPurple,
    onPrimary = white,
    secondary = yellow,
    onSecondary = purple,
    background = black,
    onBackground = white,
    surface = black,
    onSurface = white
)

private val LightColorPalette = lightColors(
    primary = purple,
    primaryVariant = darkPurple,
    onPrimary = white,
    secondary = yellow,
    secondaryVariant = green,
    onSecondary = purple,
    background = white,
    onBackground = black,
    surface = white,
    onSurface = black
)

@Composable
fun ComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}