package com.example.the7wonders.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.the7wonders.data.model.PlayerResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerResultDao {

    @Query(
        "SELECT * FROM ${
            DatabaseConstants.PLAYER_RESULTS_TABLE_NAME
        } WHERE ${
            DatabaseConstants.PLAYER_RESULTS_GAME_ID_COLUMN_NAME
        } = :gameID AND ${DatabaseConstants.PLAYER_RESULTS_PLAYER_ID_COLUMN_NAME} = :playerID"
    )
    fun getPlayerResultsForGame(
        gameID: Long,
        playerID: Long
    ): Flow<List<PlayerResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayerResults(playerResults: PlayerResultEntity)

    @Delete
    suspend fun deletePlayerResults(playerResultEntity: PlayerResultEntity)

    @Query(
        "DELETE FROM ${
            DatabaseConstants.PLAYER_RESULTS_TABLE_NAME
        } WHERE ${
            DatabaseConstants.GAME_ID_COLUMN_NAME
        } = :gameID"
    )
    suspend fun deletePlayerResultsForGame(gameID: Long)
}