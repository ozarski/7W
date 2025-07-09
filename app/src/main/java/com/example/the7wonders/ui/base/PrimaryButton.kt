package com.example.the7wonders.ui.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Typography

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    buttonColor: Color = BaseColors.onPrimary,
    textColor: Color = BaseColors.primary,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = buttonColor,
            contentColor = textColor,
        ),
    ) {
        Text(label, style = Typography.labelMedium)
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = "Primary Button Test",
        onClick = {
            println("clicked")
        }
    )
}