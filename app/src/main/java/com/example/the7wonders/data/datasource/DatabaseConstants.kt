package com.example.the7wonders.data.datasource

object DatabaseConstants {
    const val DATABASE_NAME = "gamesDB"
    const val GAME_TABLE_NAME = "Games"
    const val PLAYER_TABLE_NAME = "Players"
    const val PLAYER_RESULTS_TABLE_NAME = "PlayerResults"

    const val DEFAULT_ID_COLUMN_NAME = "id"

    const val PLAYER_DELETED_FLAG_COLUMN_NAME = "deleted"

    const val PLAYER_RESULTS_GAME_ID_COLUMN_NAME = "gameID"
    const val PLAYER_RESULTS_PLAYER_ID_COLUMN_NAME = "playerID"

    const val GAME_ID_COLUMN_NAME = "gameID"
    const val PLAYER_ID_COLUMN_NAME = "playerID"
    const val PLAYER_NAME_COLUMN_NAME = "name"
    const val DATE_COLUMN_NAME = "date"
    const val TOTAL_SCORE_COLUMN_NAME = "totalScore"
    const val PLACEMENT_COLUMN_NAME = "placement"

    const val DELETED_FLAG_TRUE = 1
    const val DELETED_FLAG_FALSE = 0
}

object DatabaseQueries {
    const val PLAYERS_WITH_RESULTS_QUERY =
        "SELECT ${DatabaseConstants.DEFAULT_ID_COLUMN_NAME}, ${DatabaseConstants.PLAYER_NAME_COLUMN_NAME}, topScore, avgPlacement, wins, games " +
                "FROM ${DatabaseConstants.PLAYER_TABLE_NAME} LEFT JOIN (" +
                "SELECT ${DatabaseConstants.PLAYER_RESULTS_PLAYER_ID_COLUMN_NAME}, " +
                "MAX(${DatabaseConstants.TOTAL_SCORE_COLUMN_NAME}) as topScore, " +
                "COUNT(${DatabaseConstants.GAME_ID_COLUMN_NAME}) as games, " +
                "AVG(${DatabaseConstants.PLACEMENT_COLUMN_NAME}) as avgPlacement " +
                "FROM PlayerResults GROUP BY ${DatabaseConstants.PLAYER_ID_COLUMN_NAME}) " +
                "ON ${DatabaseConstants.DEFAULT_ID_COLUMN_NAME} = ${DatabaseConstants.PLAYER_ID_COLUMN_NAME} LEFT JOIN (" +
                "SELECT  ${DatabaseConstants.PLAYER_ID_COLUMN_NAME} as pID, " +
                "COUNT(${DatabaseConstants.PLACEMENT_COLUMN_NAME}) as wins " +
                "FROM ${DatabaseConstants.PLAYER_RESULTS_TABLE_NAME} WHERE placement = 1 GROUP BY pID) " +
                "ON ${DatabaseConstants.DEFAULT_ID_COLUMN_NAME} = pID WHERE ${DatabaseConstants.PLAYER_DELETED_FLAG_COLUMN_NAME} = 0"
}