package com.test.sabinewsreader.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import com.test.sabinewsreader.components.dialog.ReuseableDialog
import com.test.sabinewsreader.components.dialog.ReuseableDialogInfo
import java.util.*

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = Black,
    onPrimary = Black,
    secondary = Color.White,
    secondaryVariant = Black,
    onSecondary = Black,
    error = AppRed,
    onError = AppRed,
    background = AppGrey,
    onBackground = Color.Black,
    surface = White,
    onSurface = Black
)

private val DarkColorPalette = darkColors(
    primary = AppGrey,
    primaryVariant = AppGrey,
    onPrimary = White,
    secondary = Black,
    onSecondary = Color.White,
    error = AppRed,
    onError = AppRed,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black,
    onSurface = Color.White
)

@Composable
fun SabiNewsReaderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dialogQueue: Queue<ReuseableDialogInfo>? = null,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = if (!darkTheme) AppGrey else Color.Black)
        ) {
            content()
            ProcessDialogQueue(dialogQueue = dialogQueue)
        }
    }
}

@Composable
fun ProcessDialogQueue(dialogQueue: Queue<ReuseableDialogInfo>?) {
    dialogQueue?.peek()?.let { dialogInfo ->
        ReuseableDialog(
            onDismiss = dialogInfo.onDismiss,
            title = dialogInfo.title,
            description = dialogInfo.description,
            positiveAction = dialogInfo.positiveAction,
            negativeAction = dialogInfo.negativeAction
        )
    }
}