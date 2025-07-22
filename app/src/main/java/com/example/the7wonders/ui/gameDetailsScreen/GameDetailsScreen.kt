package com.example.the7wonders.ui.gameDetailsScreen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.the7wonders.R
import com.example.the7wonders.ui.base.BackgroundOrientation
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.LoadingScreen
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Transparency
import com.example.the7wonders.ui.theme.Typography
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun GameDetailsScreen(
    navController: NavController,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    BaseBackground(orientation = BackgroundOrientation.Horizontal) {
        if (state.isLoading) {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        } else if (state.error != null) {
            navController.popBackStack()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.paddingMedium, vertical = Dimens.paddingLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.size(Dimens.paddingExtraLarge))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimens.paddingMedium,
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(R.drawable.streamline_ultimate_dice),
                                null,
                                modifier = Modifier.size(100.dp),
                                tint = BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_50)
                            )
                            GameInfo(
                                state.gameDetails.date ?: Calendar.getInstance().timeInMillis,
                                state.gameDetails.playerScores.size
                            )
                        }
                        Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                        Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                    }
                    items(state.gameDetails.playerScores) { playerScore ->
                        PlayerResultsItem(playerScore)
                        Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                    }
                }
            }
        }
    }
}

@Composable
fun GameInfo(date: Long, playerNumber: Int) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val dateString = Calendar.getInstance().apply {
        timeInMillis = date
    }.toInstant().atZone(ZoneId.systemDefault()).format(dateFormatter)
    val timeString = Calendar.getInstance().apply {
        timeInMillis = date
    }.toInstant().atZone(ZoneId.systemDefault()).format(timeFormatter)

    val borderBrush = Brush.horizontalGradient(
        listOf(
            BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_10),
            BaseColors.onSecondary.copy(alpha = Transparency.TRANSPARENCY_30),
        )
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = BaseColors.secondaryDark.copy(alpha = Transparency.TRANSPARENCY_30)),
        border = BorderStroke(width = Dimens.strokeWidthMedium, brush = borderBrush),
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(horizontal = Dimens.paddingLarge, vertical = Dimens.paddingExtraLarge),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.DateRange,
                    "date icon",
                    modifier = Modifier.size(Typography.labelLarge.lineHeight.value.dp),
                    tint = BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_70)
                )
                Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                Text(dateString, style = Typography.labelLarge, color = BaseColors.secondary)
            }
            Spacer(modifier = Modifier.size(Dimens.paddingMedium))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Outlined.Person,
                                "number of players icon",
                                modifier = Modifier.size(Typography.labelLarge.lineHeight.value.dp),
                                tint = BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_70)
                            )
                            Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                            Text(
                                playerNumber.toString(),
                                style = Typography.labelLarge,
                                color = BaseColors.secondary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painterResource(R.drawable.baseline_access_time_24),
                                "number of players icon",
                                modifier = Modifier.size(Typography.labelLarge.lineHeight.value.dp),
                                tint = BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_70)
                            )
                            Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                            Text(
                                timeString,
                                style = Typography.labelLarge,
                                color = BaseColors.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}