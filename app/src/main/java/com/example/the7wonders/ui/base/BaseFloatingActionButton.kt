package com.example.the7wonders.ui.base

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens

@Composable
fun BaseFloatingActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    color: Color = BaseColors.onSecondary,
    iconColor: Color = BaseColors.primary,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = color,
        contentColor = iconColor,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = Dimens.elevationLarge
        ),
        shape = CircleShape
    ) {
        Icon(icon, "FAB icon")
    }
}

@Preview
@Composable
fun BaseFloatingActionButtonPreview() {
    BaseFloatingActionButton(icon = Icons.Outlined.Add, modifier = Modifier.padding(16.dp)) {
        println("FAB clicked")
    }
}
