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
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.the7wonders.domain.model.BasePointTypes
import com.example.the7wonders.domain.model.PlayerResultModel
import com.example.the7wonders.domain.model.PointTypeInterface
import com.example.the7wonders.ui.base.BackgroundOrientation
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.BaseCard
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Transparency
import com.example.the7wonders.ui.theme.Typography
import kotlin.math.ceil
import kotlin.math.min

@Composable
fun PlayerResultsItem(playerResult: PlayerResultModel) {
    val expanded = remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded.value) Dimens.EXPANDED_ARROW_ROTATION_DEGREES else Dimens.COLLAPSED_ARROW_ROTATION_DEGREES,
        animationSpec = spring(Spring.DampingRatioMediumBouncy)
    )
    val scores = getViableScores(playerResult.scores)
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
                        fontSize = Dimens.extraLargeFontSize,
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
                    spring(Spring.DampingRatioLowBouncy)
                )
            ) {
                if (expanded.value) {
                    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                        val availableWidth = maxWidth
                        val itemWidth = Dimens.scoreGridItemWidth
                        val minGap = Dimens.paddingMedium
                        val columns = ((availableWidth + minGap) / (itemWidth + minGap))
                            .toInt().coerceIn(2, 5)

                        val viableScores = scores.filter { it.second != null }
                        val rows = ceil(viableScores.size.toDouble() / columns).toInt()

                        Column {
                            for (r in 0 until rows) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    repeat(columns) { col ->
                                        val idx = r * columns + col
                                        if (idx < viableScores.size) {
                                            ScoreGridItem(viableScores[idx])
                                        } else {
                                            ScoreGridItem(
                                                Pair(BasePointTypes.Wonder, null),
                                                modifier = Modifier.alpha(0f)
                                            )
                                        }
                                    }
                                }
                                if (r < rows - 1) {
                                    Spacer(modifier = Modifier.size(Dimens.paddingMedium))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getViableScores(scores: List<Pair<PointTypeInterface, Int?>>): List<Pair<PointTypeInterface, Int?>> {
    val viableScores = mutableListOf<Pair<PointTypeInterface, Int?>>()
    for (score in scores) {
        if (score.second != null) {
            viableScores.add(score)
        }
    }
    return viableScores
}

@Composable
fun ScoreGridItem(score: Pair<PointTypeInterface, Int?>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = Dimens.paddingSmall)
            .height(IntrinsicSize.Min)
            .width(Dimens.scoreGridItemWidth),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
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
        Spacer(Modifier.size(Dimens.paddingSmall))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                if (score.second != null) score.second.toString() else "0",
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
            Pair(BasePointTypes.Wonder, 10),
            Pair(BasePointTypes.Military, 10),
            Pair(BasePointTypes.Gold, 10),
            Pair(BasePointTypes.Blue, 10),
            Pair(BasePointTypes.Yellow, 10),
            Pair(BasePointTypes.Green, 10),
            Pair(BasePointTypes.Purple, 10),
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