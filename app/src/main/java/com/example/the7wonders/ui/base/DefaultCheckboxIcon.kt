package com.example.the7wonders.ui.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens

@Composable
fun DefaultCheckboxIcon(checked: MutableState<Boolean>) {
    Icon(
        imageVector = if (checked.value) Icons.Filled.Done else Icons.Filled.Clear,
        tint = if (checked.value) BaseColors.textPrimary else BaseColors.textSecondary,
        contentDescription = "checkbox icon",
        modifier = Modifier.padding(
            horizontal = Dimens.paddingMedium,
            vertical = Dimens.paddingMedium
        )
    )
}