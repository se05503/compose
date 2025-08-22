package com.example.compose_practice.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.compose_practice.ui.theme.color.ColorSet

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.LightColors }

@Composable
fun ComposePracticeTheme(
    myColors: ColorSet,
    darkTheme: Boolean = isSystemInDarkTheme(), // 시스템에서 파악
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        myColors.DarkColors
    } else {
        myColors.LightColors
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material, // 테마에서 사용되는 색상
            typography = Typography, // 테마에서 사용되는 서체
            shapes = Shapes, // 테마에서 사용되는 모양
            content = content
        )
    }
}