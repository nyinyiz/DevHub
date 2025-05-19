package com.nyinyi.devhub.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Dark color scheme
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary, //using ruby for tertiary.
    background = Background,
    surface = Background,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = PrimaryText,
    onSurface = PrimaryText,
    primaryContainer = Primary, // Added primary container
    onPrimaryContainer = Color.White,
)

// Light color scheme
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,  // Using ruby for tertiary.
    background = Background,
    surface = Background,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = PrimaryText,
    onSurface = PrimaryText,
    primaryContainer = Primary, // Added primary container
    onPrimaryContainer = Color.White,
)

@Composable
fun DevHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // set to false
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colorScheme.primaryContainer, // Set system bar color
            darkIcons = false // Light text for status bar
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Preview
@Composable
fun ThemePreview() {
    DevHubTheme {
        Surface {
            Text("Hello DevHub", style = MaterialTheme.typography.bodyMedium)
        }
    }
}