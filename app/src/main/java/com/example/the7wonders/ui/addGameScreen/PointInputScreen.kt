package com.example.the7wonders.ui.addGameScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.R
import com.example.the7wonders.ui.base.BaseBackground
import com.example.the7wonders.ui.base.BaseInputField
import com.example.the7wonders.ui.base.PrimaryButton
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun PointInputScreen(viewModel: AddGameViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val currentPoint = state.currentInputPoint
    val context = LocalContext.current
    BaseBackground {
        if (currentPoint != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .widthIn(max = 350.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(250.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Crossfade(targetState = currentPoint.pointType) { type ->
                            Icon(
                                painterResource(type.icon),
                                "point type icon",
                                tint = type.color,
                                modifier = Modifier.size(Dimens.addPlayersIconSize)
                            )
                        }
                    }

                    Crossfade(targetState = currentPoint.playerName) { name ->
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                            Text(
                                stringResource(
                                    R.string.point_type_for_player,
                                    currentPoint.pointType.pointName,
                                    name,
                                ),
                                fontFamily = FontFamily.Monospace,
                                style = Typography.labelLarge,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                    BaseInputField(
                        value = if (currentPoint.value == 0) "" else currentPoint.value.toString(),
                        hint = stringResource(R.string.points_input_hint),
                        onValueChange = {
                            viewModel.updateCurrentPointValue(it.text)
                        },
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimens.paddingSmall)
                    )
                    Spacer(modifier = Modifier.size(Dimens.paddingLarge))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .padding(horizontal = Dimens.paddingSmall)
                    ) {
                        val prevButtonVisible = !state.confirmedPoints.isEmpty()
                        AnimatedVisibility(
                            visible = prevButtonVisible,
                            enter = fadeIn() + expandHorizontally(),
                            exit = fadeOut() + shrinkHorizontally(),
                        ) {
                            PrimaryButton(
                                label = stringResource(R.string.previous_button_label),
                                textColor = BaseColors.textSecondary,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = Dimens.paddingMedium)
                            ) {
                                viewModel.rollBackPointValue()
                            }
                        }
                        PrimaryButton(
                            label = stringResource(R.string.next_button_label),
                            buttonColor = BaseColors.onSecondary,
                            textColor = BaseColors.primary,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            viewModel.insertPointValue()
                        }
                    }
                }
            }
        }
    }
}