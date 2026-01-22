package com.example.the7wonders.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.the7wonders.data.model.GameEntity
import com.example.the7wonders.data.model.GameWithPlayerDetailsDto
import com.example.the7wonders.data.model.GameWithResultsDto
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query(
        "SELECT * FROM ${
            DatabaseConstants.GAME_TABLE_NAME
        }"
    )
    fun getGames(): Flow<List<GameEntity>>

    @Query(
        "SELECT * FROM ${
            DatabaseConstants.GAME_TABLE_NAME
        } WHERE ${
            DatabaseConstants.DEFAULT_ID_COLUMN_NAME
        } = :id"
    )
    suspend fun getGameById(id: Long): GameEntity?

    @Query(
        "SELECT " +
                "${DatabaseConstants.GAME_ID_COLUMN_NAME}, " +
                "${DatabaseConstants.PLAYER_ID_COLUMN_NAME}, " +
                "${DatabaseConstants.PLAYER_NAME_COLUMN_NAME}, " +
                "${DatabaseConstants.DATE_COLUMN_NAME}, " +
                "${DatabaseConstants.TOTAL_SCORE_COLUMN_NAME} " +
                "FROM ${DatabaseConstants.PLAYER_RESULTS_TABLE_NAME} INNER JOIN ${DatabaseConstants.GAME_TABLE_NAME} " +
                "ON ${DatabaseConstants.GAME_ID_COLUMN_NAME} = ${DatabaseConstants.GAME_TABLE_NAME}.${DatabaseConstants.DEFAULT_ID_COLUMN_NAME} " +
                "INNER JOIN ${DatabaseConstants.PLAYER_TABLE_NAME} " +
                "ON ${DatabaseConstants.PLAYER_ID_COLUMN_NAME} = ${DatabaseConstants.PLAYER_TABLE_NAME}.${DatabaseConstants.DEFAULT_ID_COLUMN_NAME}" +
                " ORDER BY ${DatabaseConstants.DATE_COLUMN_NAME} DESC"
    )
    fun getGamesWithResults(): Flow<List<GameWithResultsDto>>

    @Query(
        "SELECT ${DatabaseConstants.PLAYER_RESULTS_TABLE_NAME}.*, " +
                "${DatabaseConstants.PLAYER_TABLE_NAME}.${DatabaseConstants.PLAYER_NAME_COLUMN_NAME}, " +
                "${DatabaseConstants.GAME_TABLE_NAME}.${DatabaseConstants.DATE_COLUMN_NAME} FROM " +
                "${DatabaseConstants.PLAYER_RESULTS_TABLE_NAME} INNER JOIN ${DatabaseConstants.GAME_TABLE_NAME} ON " +
                "${DatabaseConstants.GAME_ID_COLUMN_NAME} = ${DatabaseConstants.GAME_TABLE_NAME}.${DatabaseConstants.DEFAULT_ID_COLUMN_NAME} " +
                "INNER JOIN ${DatabaseConstants.PLAYER_TABLE_NAME} ON " +
                "${DatabaseConstants.PLAYER_ID_COLUMN_NAME} = ${DatabaseConstants.PLAYER_TABLE_NAME}.${DatabaseConstants.DEFAULT_ID_COLUMN_NAME} " +
                "WHERE ${DatabaseConstants.GAME_ID_COLUMN_NAME} = :id"
    )
    suspend fun getGameDetails(id: Long): List<GameWithPlayerDetailsDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGame(gameEntity: GameEntity): Long

    @Delete
    suspend fun deleteGame(gameEntity: GameEntity)

}