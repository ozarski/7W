package com.example.the7wonders.domain.model

import androidx.compose.ui.graphics.Color
import com.example.the7wonders.R
import com.example.the7wonders.ui.theme.PointTypeColors

enum class PointType(val pointName: String, val color: Color, val icon: Int) {
    Wonder("Wonder", PointTypeColors.wonder, R.drawable.mingcute_egyptian_pyramids),
    Military("Military", PointTypeColors.military, R.drawable.rounded_swords_24),
    Gold("Gold", PointTypeColors.gold, R.drawable.rounded_money_bag_24),
    Blue("Blue card", PointTypeColors.blue, R.drawable.rounded_theater_comedy_24),
    Yellow("Yellow card", PointTypeColors.yellow, R.drawable.hugeicons_camel),
    Green("Green card", PointTypeColors.green, R.drawable.rounded_architecture_24),
    Purple("Purple card", PointTypeColors.purple, R.drawable.pop_hammer_sledge)
}