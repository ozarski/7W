package com.example.the7wonders.ui.tabsScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.the7wonders.ui.base.BaseFloatingActionButton
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun TabsBar(
    modifier: Modifier = Modifier,
    selected: MainTabs,
    onTabSelected: (tab: MainTabs) -> Unit
) {
    Box(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = Dimens.paddingExtraLarge, vertical = Dimens.paddingMedium)
                .shadow(Dimens.elevationMedium, shape = RoundedCornerShape(Dimens.cornerRadiusMax))
                .background(
                    shape = RoundedCornerShape(
                        Dimens.cornerRadiusMax
                    ), color = BaseColors.onSecondary
                )
                .align(alignment = Alignment.BottomCenter)
        ) {
            TabItem(selected, MainTabs.Games, onTabSelected)
            Spacer(modifier = Modifier.width(Dimens.tabBarSpacing))
            TabItem(selected, MainTabs.Players, onTabSelected)
        }
        AddButton(selected, modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
fun AddButton(selected: MainTabs, modifier: Modifier) {
    BaseFloatingActionButton(
        modifier = modifier.padding(bottom = Dimens.paddingExtraLarge),
        color = BaseColors.secondary,
        iconColor = BaseColors.onSecondary,
        icon = Icons.Filled.Add
    ) {
        //TODO("add game/player depending on the active tab")
        if (selected == MainTabs.Games) {
            println("Add game")
        } else {
            println("Add player")
        }
    }
}

@Composable
fun TabItem(selected: MainTabs, screen: MainTabs, onTabSelected: (MainTabs) -> Unit) {
    val isSelected = screen == selected
    val animatedColor = animateColorAsState(
        targetValue = if (isSelected) BaseColors.primary else BaseColors.onSecondary,
        animationSpec = tween(Dimens.ANIMATION_DURATION_MEDIUM)
    )
    Box(
        modifier = Modifier
            .padding(
                vertical = Dimens.paddingMedium,
                horizontal = Dimens.paddingMedium
            )
            .clickable(
                interactionSource = null,
                onClick = {
                    onTabSelected(screen)
                },
                indication = null
            )
            .background(
                shape = RoundedCornerShape(Dimens.cornerRadiusMax),
                color = animatedColor.value
            ),
    ) {
        Text(
            screen.name,
            style = Typography.titleLarge,
            modifier = Modifier
                .widthIn(Dimens.tabItemMinWidth)
                .padding(
                    vertical = Dimens.paddingMedium,
                ),
            color = if (isSelected) BaseColors.onSecondary else BaseColors.primary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun TabsBarPreview() {
    val screen = MainTabs.Games
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize()
    ) {
        TabsBar(selected = screen) { tab ->
            println("selected tab ${tab.name} ")
        }
    }
}