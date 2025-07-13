package com.example.the7wonders.ui.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseCard(modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable () -> Unit) {

    CompositionLocalProvider(
        LocalRippleConfiguration provides RippleConfiguration(color = BaseColors.onSecondary)
    ) {
        Card(
            modifier = modifier,
            onClick = onClick,
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.elevationSmall),
            shape = RoundedCornerShape(Dimens.cornerRadiusExtraLarge),
            colors = CardDefaults.cardColors(containerColor = BaseColors.primary),
            border = BorderStroke(Dimens.strokeWidthMedium, BaseColors.secondary),
        ) {
            Box(modifier = Modifier.padding(Dimens.paddingLarge)) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun BaseCardPreview() {
    BaseCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        onClick = { println("card clicked") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "CARD TEST CONTENT",
                textAlign = TextAlign.Center
            )
        }
    }
}