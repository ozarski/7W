package com.example.the7wonders.ui.tabsScreen.gamesTab

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.the7wonders.R
import com.example.the7wonders.domain.model.GameItem
import com.example.the7wonders.domain.model.PlayerItem
import com.example.the7wonders.ui.base.BaseCard
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameListItem(game: GameItem) {

    val leaderboard = game.playerScores.sortedByDescending { it.second }
        .filterIndexed { index, pair -> index < 5 }

    val format = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ROOT)
    val dateFormatted =
        Calendar.getInstance().apply { timeInMillis = game.date }.toInstant()
            .atZone(ZoneId.systemDefault()).format(format)
    BaseCard(
        onClick = {
            //TODO("open game details")
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(leaderboard, dateFormatted)
            Spacer(modifier = Modifier.size(Dimens.paddingLarge))
            PlayerList(leaderboard)
            Spacer(modifier = Modifier.size(Dimens.paddingSmall))
        }
    }
}

@Composable
fun PlayerList(leaderboard: List<Pair<PlayerItem, Int>>) {
    leaderboard.subList(1, leaderboard.size).forEachIndexed { index, playerScore ->
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                Text(
                    "${index + 2}.",
                    style = Typography.labelLarge,
                    color = BaseColors.textSecondary
                )
                Spacer(modifier = Modifier.size(Dimens.paddingSmall))
                Text(playerScore.first.name, style = Typography.labelLarge)
            }
            Text("${playerScore.second} pts", style = Typography.labelLarge)
        }
        if (index != leaderboard.size - 2) Spacer(modifier = Modifier.size(Dimens.paddingMedium))
    }
}

@Composable
fun TopBar(leaderboard: List<Pair<PlayerItem, Int>>, dateFormatted: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(R.drawable.rounded_crown_24),
                "winner icon",
                tint = BaseColors.winIconColor,
                modifier = Modifier.size(Dimens.iconSizeLarge)
            )
            Spacer(modifier = Modifier.size(Dimens.paddingMedium))
            Column {
                Text(leaderboard.first().first.name, style = Typography.titleSmall)
                Text(
                    "${leaderboard.first().second} points",
                    style = Typography.bodyLarge
                )
            }
        }
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(top = Dimens.paddingSmall)
        ) {
            Icon(
                Icons.Outlined.DateRange,
                "game date icon",
                modifier = Modifier.size(Dimens.iconSizeSmall),
                tint = BaseColors.textSecondary
            )
            Spacer(modifier = Modifier.size(Dimens.paddingMedium))
            Text(
                dateFormatted,
                style = Typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                color = BaseColors.textSecondary
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun GameListItemPreview() {
    val playerScores = listOf(
        Pair(PlayerItem(1, "Player one"), 12),
        Pair(PlayerItem(2, "Player two"), 23),
        Pair(PlayerItem(3, "Player three"), 34),
        Pair(PlayerItem(4, "Player four"), 45),
        Pair(PlayerItem(5, "Player five"), 56),
    )
    val gameItem = GameItem(
        id = 1,
        date = Calendar.getInstance().timeInMillis,
        playerScores = playerScores
    )

    Column(modifier = Modifier.padding(Dimens.paddingMedium)) {
        GameListItem(game = gameItem)
    }
}