package com.example.the7wonders.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun BaseCheckbox(
    modifier: Modifier = Modifier,
    checked: MutableState<Boolean>,
    checkedIcon: @Composable () -> Unit = { DefaultCheckboxIcon(checked) },
    label: String,
) {
    Surface(
        onClick = {
            checked.value = !checked.value
        },
        shape = RoundedCornerShape(Dimens.cornerRadiusMax),
        color = BaseColors.primary,
        modifier = modifier
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            checkedIcon()
            Text(
                label,
                style = Typography.labelMedium,
                modifier = Modifier.padding(
                    horizontal = Dimens.paddingLarge,
                    vertical = Dimens.paddingMedium
                ),
                color = if (checked.value) BaseColors.textPrimary else BaseColors.textSecondary,
            )
        }
    }
}

@Composable
@Preview
fun BaseCheckboxPreview() {
    val checked = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseColors.backgroundSecondary)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseCheckbox(checked = checked, label = "checkbox test", modifier = Modifier.fillMaxWidth())
    }
}