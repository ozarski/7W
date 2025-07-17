package com.example.the7wonders.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.the7wonders.data.model.PlayerResultsEntity

@Dao
interface PlayerResultsDao {

    @Query(
        "SELECT * FROM ${
            DatabaseConstants.PLAYER_RESULTS_TABLE_NAME
        } WHERE ${
            DatabaseConstants.PLAYER_RESULTS_GAME_ID_COLUMN_NAME
        } = :gameID AND ${DatabaseConstants.PLAYER_RESULTS_PLAYER_ID_COLUMN_NAME} = :playerID"
    )
    suspend fun getPlayerResultsForGame(gameID: Long, playerID: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayerResults(playerResults: PlayerResultsEntity)

    @Delete
    fun deletePlayerResults(playerResultsEntity: PlayerResultsEntity)

}