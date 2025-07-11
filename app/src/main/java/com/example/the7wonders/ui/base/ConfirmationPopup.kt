package com.example.the7wonders.ui.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun ConfirmationPopup(
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {
    BasePopupContainer(onDismiss = onNegativeClick) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(Dimens.spacerSizeLarge))
            Text(
                text = title,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(Dimens.spacerSizeExtraLarge))
            Text(
                text = message,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(Dimens.spacerSizeExtraLarge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PrimaryButton(
                    label = positiveButtonText,
                    buttonColor = BaseColors.success,
                    onClick = onPositiveClick
                )
                PrimaryButton(
                    label = negativeButtonText,
                    buttonColor = BaseColors.error,
                    onClick = onNegativeClick
                )
            }
            Spacer(modifier = Modifier.size(Dimens.spacerSizeLarge))
        }
    }
}

@Composable
@Preview
fun ConfirmationPopupPreview() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConfirmationPopup(
            "TEST TITLE",
            "test confirmation question",
            "yes text",
            "no text",
            onPositiveClick = {
                println("YES CLICKED")
            },
            onNegativeClick = {
                println("NO CLICKED")
            }
        )
    }
}