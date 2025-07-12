package com.example.the7wonders.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Typography

@Composable
fun BaseBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    val brush =
        Brush.linearGradient(
            listOf(
                BaseColors.backgroundPrimary,
                BaseColors.backgroundGradientSecondary
            )
        )

    Box(
        modifier = modifier.background(brush),
    ) {
        content()
    }
}

@Composable
@Preview
fun BaseBackgroundPreview() {
    BaseBackground(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("TEST BACKGROUND CONTENT", style = Typography.titleLarge)
        }
    }
}