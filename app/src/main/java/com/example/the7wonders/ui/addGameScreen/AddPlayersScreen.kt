package com.example.the7wonders.ui.addGameScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.R
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.PrimaryButton
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens

@Composable
fun AddPlayersScreen(viewModel: AddGameViewModel = hiltViewModel()) {
    BaseBackground {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = Dimens.addPlayerScreenPaddingBottom)
        ) {
            Spacer(modifier = Modifier.size(Dimens.paddingExtraLarge))
            Icon(
                Icons.Outlined.Person,
                null,
                modifier = Modifier.size(Dimens.addPlayersIconSize),
                tint = BaseColors.onSecondary
            )
            Spacer(modifier = Modifier.size(Dimens.paddingExtraLarge))
            AddPlayerList()
            Spacer(modifier = Modifier.size(Dimens.paddingExtraLarge))
            PrimaryButton(
                label = stringResource(R.string.continue_button_label),
                buttonColor = BaseColors.onSecondary,
                textColor = BaseColors.primary,
                modifier = Modifier.width(Dimens.addPlayerListWidth)
            ) {
                viewModel.confirmPlayers()
            }
        }
    }
}