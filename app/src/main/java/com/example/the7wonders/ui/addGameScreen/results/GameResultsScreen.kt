package com.example.the7wonders.ui.addGameScreen.results

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.the7wonders.R
import com.example.the7wonders.domain.model.PlayerResultModel
import com.example.the7wonders.ui.addGameScreen.AddGameViewModel
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.PrimaryButton
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun GameResultsScreen(
    viewModel: AddGameViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.value
    val top3 = state.results.subList(0, if (state.results.size < 3) state.results.size else 3)
    BaseBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.size(Dimens.gameResultsTopSpacing))
                PodiumRow(top3)
                if (state.results.size > 3) {
                    val leaderboard = state.results.subList(3, state.results.size)
                    Column(
                        modifier = Modifier
                            .padding(horizontal = Dimens.paddingExtraLarge)
                            .padding(top = Dimens.paddingExtraLarge)
                            .background(
                                shape = RoundedCornerShape(Dimens.cornerRadiusExtraLarge),
                                color = BaseColors.primary
                            )
                            .border(
                                width = Dimens.strokeWidthMedium,
                                color = BaseColors.onSecondary,
                                shape = RoundedCornerShape(Dimens.cornerRadiusExtraLarge),
                            ),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.size(Dimens.paddingExtraLarge))
                        leaderboard.forEach { result ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = Dimens.paddingLarge)
                            ) {
                                Row {
                                    Text(
                                        "${result.placement}.",
                                        style = Typography.labelLarge,
                                        color = BaseColors.textSecondary
                                    )
                                    Spacer(modifier = Modifier.size(Dimens.paddingSmall))
                                    Text(result.playerName, style = Typography.labelLarge)
                                }
                                Text("${result.totalScore} pts", style = Typography.labelLarge)
                            }
                            Spacer(modifier = Modifier.size(Dimens.paddingExtraLarge))
                        }
                    }
                }
            }
            PrimaryButton(
                label = "Done",
                buttonColor = BaseColors.onSecondary,
                textColor = BaseColors.secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingExtraLarge)
            ) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun PodiumRow(top3: List<PlayerResultModel>) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        if (top3.size > 2) {
            PlayerResultsBubble(
                results = top3.last(),
                color = BaseColors.thirdPlaceColor,
                iconID = R.drawable.rounded_workspace_premium_24,
                modifier = Modifier.padding(top = 50.dp)
            )
        } else {
            EmptyBubble(
                color = BaseColors.thirdPlaceColor,
                iconID = R.drawable.rounded_workspace_premium_24,
                modifier = Modifier.padding(top = 50.dp)
            )
        }
        PlayerResultsBubble(
            results = top3.first(),
            color = BaseColors.winIconColor,
            iconID = R.drawable.rounded_crown_24
        )
        PlayerResultsBubble(
            results = top3[1],
            color = BaseColors.secondPlaceColor,
            iconID = R.drawable.rounded_workspace_premium_24,
            modifier = Modifier.padding(top = 50.dp)
        )
    }
}