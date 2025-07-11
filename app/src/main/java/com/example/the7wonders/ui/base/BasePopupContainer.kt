package com.example.the7wonders.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens

@Composable
fun BasePopupContainer(onDismiss: () -> Unit, popupBody: @Composable () -> Unit) {

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .border(
                    width = Dimens.strokeWidthMedium, color = BaseColors.secondary,
                    shape = RoundedCornerShape(Dimens.cornerRadiusExtraLarge)
                )
                .background(
                    color = BaseColors.primary,
                    shape = RoundedCornerShape(Dimens.cornerRadiusExtraLarge)
                )
                .padding(Dimens.paddingMedium)
        ) {
            popupBody()
        }
    }
}

@Composable
@Preview
fun BasePopupContainerPreview() {
    BasePopupContainer(onDismiss = {}) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("base popup container test")
        }
    }
}