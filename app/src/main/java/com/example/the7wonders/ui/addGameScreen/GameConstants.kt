package com.example.the7wonders.ui.addGameScreen

import androidx.compose.ui.graphics.Color
import com.example.the7wonders.R
import com.example.the7wonders.ui.theme.PointTypeColors

enum class PointTypes(val pointName: String, val color: Color, val icon: Int) {
    Wonder("Wonder", PointTypeColors.wonder, R.drawable.outlined_rounded_building_24),
    Military("Military", PointTypeColors.military, R.drawable.rounded_swords_24),
    Gold("Gold", PointTypeColors.gold, R.drawable.rounded_money_bag_24),
    Blue("Blue card", PointTypeColors.blue, R.drawable.rounded_theater_comedy_24),
    Yellow("Yellow card", PointTypeColors.yellow, R.drawable.rounded_compare_arrows_24),
    Green("Green card", PointTypeColors.green, R.drawable.rounded_architecture_24),
    Purple("Purple card", PointTypeColors.purple, R.drawable.rounded_playing_cards_24)
}