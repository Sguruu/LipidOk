package com.sguru.lipidok.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    // кнопки
    primary = Wave,
    secondary = Blue,
    // цвет текста
    tertiary = Black,
    // цвет фона
    background = White,
    // цвет текста
    // surface = White,
    // цвет текста в toolBar
    onSurface = White,
    // цвет ToolBar
    surface = Wave,
    // навигашей бар вариант
    onSurfaceVariant = White,
    // выбранный эллемнт в навигайшен бар
    onSecondaryContainer = Wave,
    secondaryContainer = White
    // surfaceTint = Blue,
//    surfaceVariant = Blue

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    // цвет текста
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
        onSurface = Color(0xFF1C1B1F),
    */


//    primary: Color = ColorLightTokens.Primary,
//onPrimary: Color = ColorLightTokens.OnPrimary,
//primaryContainer: Color = ColorLightTokens.PrimaryContainer,
//onPrimaryContainer: Color = ColorLightTokens.OnPrimaryContainer,
//inversePrimary: Color = ColorLightTokens.InversePrimary,
//secondary: Color = ColorLightTokens.Secondary,
//onSecondary: Color = ColorLightTokens.OnSecondary,
//secondaryContainer: Color = ColorLightTokens.SecondaryContainer,
//onSecondaryContainer: Color = ColorLightTokens.OnSecondaryContainer,
//tertiary: Color = ColorLightTokens.Tertiary,
//onTertiary: Color = ColorLightTokens.OnTertiary,
//tertiaryContainer: Color = ColorLightTokens.TertiaryContainer,
//onTertiaryContainer: Color = ColorLightTokens.OnTertiaryContainer,
//background: Color = ColorLightTokens.Background,
//onBackground: Color = ColorLightTokens.OnBackground,
//surface: Color = ColorLightTokens.Surface,
//onSurface: Color = ColorLightTokens.OnSurface,
//surfaceVariant: Color = ColorLightTokens.SurfaceVariant,
//onSurfaceVariant: Color = ColorLightTokens.OnSurfaceVariant,
//surfaceTint: Color = primary,
//inverseSurface: Color = ColorLightTokens.InverseSurface,
//inverseOnSurface: Color = ColorLightTokens.InverseOnSurface,
//error: Color = ColorLightTokens.Error,
//onError: Color = ColorLightTokens.OnError,
//errorContainer: Color = ColorLightTokens.ErrorContainer,
//onErrorContainer: Color = ColorLightTokens.OnErrorContainer,
//outline: Color = ColorLightTokens.Outline,
//outlineVariant: Color = ColorLightTokens.OutlineVariant,
//scrim: Color = ColorLightTokens.Scrim,
)

@Composable
fun LipidOkTheme(
    content: @Composable () -> Unit
) {

    val systemUiController: SystemBarStyle

    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Wave.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}