package com.example.the7wonders.ui.addGameScreen.playerSelection

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.the7wonders.R
import com.example.the7wonders.ui.addGameScreen.AddGameViewModel
import com.example.the7wonders.ui.base.BaseCheckbox
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens


@Composable
fun AddPlayerList(viewModel: AddGameViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    val iconsMap = mapOf<Int?, Painter>(
        1 to painterResource(R.drawable.rounded_counter_1_24),
        2 to painterResource(R.drawable.rounded_counter_2_24),
        3 to painterResource(R.drawable.rounded_counter_3_24),
        4 to painterResource(R.drawable.rounded_counter_4_24),
        5 to painterResource(R.drawable.rounded_counter_5_24),
        6 to painterResource(R.drawable.rounded_counter_6_24),
        7 to painterResource(R.drawable.rounded_counter_7_24),
    )

    Column(
        modifier = Modifier
            .heightIn(max = Dimens.addPlayerListMaxHeight)
            .width(Dimens.addPlayerListWidth)
            .border(
                width = Dimens.strokeWidthMedium,
                color = BaseColors.secondary,
                shape = RoundedCornerShape(Dimens.cornerRadiusLarge)
            )
            .background(
                shape = RoundedCornerShape(Dimens.cornerRadiusLarge),
                color = BaseColors.primary
            )
    ) {
        LazyColumn(modifier = Modifier.padding(horizontal = Dimens.paddingLarge)) {
            item { Spacer(modifier = Modifier.size(Dimens.paddingLarge)) }
            items(state.availablePlayers.size) { index ->
                val player = state.availablePlayers[index]
                Crossfade(
                    targetState = player
                ) { player ->
                    BaseCheckbox(
                        modifier = Modifier.fillMaxWidth(),
                        checked = player.isPlaying,
                        checkedIcon = iconsMap.getOrDefault(
                            player.ordinal,
                            rememberVectorPainter(Icons.Outlined.Close)
                        ),
                        uncheckedIcon = rememberVectorPainter(Icons.Outlined.Close),
                        label = player.name,
                        colorUnchecked = BaseColors.secondary,
                        colorChecked = BaseColors.onSecondary,
                        contentColorUnchecked = BaseColors.textPrimary,
                        contentColorChecked = BaseColors.primary
                    ) {
                        viewModel.togglePlayer(index)
                    }
                }
            }
            item { Spacer(modifier = Modifier.size(Dimens.paddingLarge)) }
        }
    }
}


//val viewModel = AddGameViewModel()
//
//@Composable
//@Preview
//fun AddPlayerListPreview() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        AddPlayerList(viewModel = viewModel)
//    }
//}