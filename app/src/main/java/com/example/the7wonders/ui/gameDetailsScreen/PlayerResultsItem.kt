package com.example.the7wonders.ui.gameDetailsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.the7wonders.R
import com.example.the7wonders.domain.model.PlayerResultModel
import com.example.the7wonders.domain.model.PointType
import com.example.the7wonders.ui.base.BackgroundOrientation
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.BaseCard
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Transparency
import com.example.the7wonders.ui.theme.Typography

@Composable
fun PlayerResultsItem(playerResult: PlayerResultModel) {
    val expanded = remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded.value) Dimens.EXPANDED_ARROW_ROTATION_DEGREES else Dimens.COLLAPSED_ARROW_ROTATION_DEGREES,
        animationSpec = spring(Spring.DampingRatioMediumBouncy)
    )
    BaseCard(
        onClick = {
            expanded.value = !expanded.value
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.paddingMedium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        playerResult.placement.toString(),
                        fontSize = Dimens.playerResultDetailsPlacementFontSize,
                        color = BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_90)
                    )
                    Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                    Column {
                        Text(
                            playerResult.playerName,
                            style = Typography.labelLarge,
                            modifier = Modifier.widthIn(max = Dimens.gameListItemPlayerNameMaxWidth),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            stringResource(R.string.points_format, playerResult.totalScore),
                            style = Typography.labelMedium
                        )
                    }
                }
                Icon(
                    painterResource(R.drawable.outline_expand_circle_down_24),
                    "expand details icon",
                    modifier = Modifier
                        .size(Dimens.iconSizeMedium)
                        .rotate(rotationAngle),
                    tint = BaseColors.secondary.copy(alpha = Transparency.TRANSPARENCY_70)
                )
            }
            AnimatedVisibility(
                visible = expanded.value,
                enter = fadeIn(keyframes {
                    this.durationMillis = Dimens.ANIMATION_DURATION_LONG
                }) + expandVertically(
                    spring(Spring.DampingRatioLowBouncy)
                ),
                exit = fadeOut(keyframes {
                    this.durationMillis = Dimens.ANIMATION_DURATION_LONG
                }) + shrinkVertically(
                    keyframes { this.durationMillis = Dimens.ANIMATION_DURATION_SHORT }
                ),
            ) {
                if (expanded.value) {
                    Column {
                        Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ScoreGridItem(playerResult.scores[PointType.Wonder.ordinal])
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            ScoreGridItem(playerResult.scores[PointType.Military.ordinal])
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            ScoreGridItem(playerResult.scores[PointType.Gold.ordinal])
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            ScoreGridItem(playerResult.scores[PointType.Blue.ordinal])
                        }
                        Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ScoreGridItem(playerResult.scores[PointType.Yellow.ordinal])
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            ScoreGridItem(playerResult.scores[PointType.Green.ordinal])
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            ScoreGridItem(playerResult.scores[PointType.Purple.ordinal])
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            ScoreGridItem(
                                playerResult.scores[PointType.Purple.ordinal],
                                modifier = Modifier.alpha(Transparency.TRANSPARENCY_0)
                            )
                        }
                        Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreGridItem(score: Pair<PointType, Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = Dimens.paddingSmall)
            .height(IntrinsicSize.Min)
            .width(Dimens.scoreGridItemWidth),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = score.first.color.copy(alpha = Transparency.TRANSPARENCY_70),
                    shape = RoundedCornerShape(Dimens.cornerRadiusLarge)
                )
                .padding(Dimens.paddingSmall)
                .size(Dimens.iconSizeMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painterResource(score.first.icon),
                "${score.first.pointName} point type icon",
                modifier = Modifier.size(Dimens.iconSizeMedium)
            )
        }
        Spacer(Modifier.size(Dimens.paddingMedium))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                score.second.toString(),
                style = Typography.labelLarge
            )
            Text(stringResource(R.string.pts_label), style = Typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun PlayerResultsItemPreview() {
    val results = PlayerResultModel(
        playerID = 1,
        playerName = "Player One",
        totalScore = 70,
        placement = 1,
        scores = listOf(
            Pair(PointType.Wonder, 10),
            Pair(PointType.Military, 10),
            Pair(PointType.Gold, 10),
            Pair(PointType.Blue, 10),
            Pair(PointType.Yellow, 10),
            Pair(PointType.Green, 10),
            Pair(PointType.Purple, 10),
        )
    )

    BaseBackground(orientation = BackgroundOrientation.Horizontal) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PlayerResultsItem(results)
        }
    }
}