package com.example.the7wonders.ui.tabsScreen.playersTab.addPlayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.ui.base.BaseInputField
import com.example.the7wonders.ui.base.BasePopupContainer
import com.example.the7wonders.ui.base.PrimaryButton
import com.example.the7wonders.ui.tabsScreen.playersTab.PlayerListViewModel
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens

@Composable
fun AddPlayerPopup(
    onDismiss: () -> Unit,
    onAdd: (String) -> Unit
) {
    val playerName = remember { mutableStateOf("") }

    BasePopupContainer(
        onDismiss = onDismiss
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Min)
                .padding(horizontal = Dimens.paddingSmall)
        ) {
            Icon(
                Icons.Outlined.Person,
                "add player popup icon",
                modifier = Modifier.size(Dimens.addPlayerPopupIconSize)
            )
            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
            BaseInputField(
                value = playerName.value,
                hint = "player name",
            ) { newName ->
                playerName.value = newName
            }
            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PrimaryButton(
                    label = "Cancel",
                    modifier = Modifier.weight(1f)
                ) {
                    onDismiss()
                }
                Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                PrimaryButton(
                    label = "Add",
                    modifier = Modifier.weight(1f),
                    buttonColor = BaseColors.onSecondary
                ) {
                    onAdd(playerName.value)
                }
            }
            Spacer(modifier = Modifier.size(Dimens.paddingMedium))
        }
    }
}

@Composable
@Preview
fun AddPlayerPopupPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AddPlayerPopup(
            onDismiss = { println("add player popup dismissed") },
            onAdd = { println("player $it added") }
        )
    }
}