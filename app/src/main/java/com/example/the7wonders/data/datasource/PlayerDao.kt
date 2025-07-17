package com.example.the7wonders.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.the7wonders.data.model.PlayerEntity
import com.example.the7wonders.data.model.PlayerWithStatsDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query(
        "SELECT * FROM ${
            DatabaseConstants.PLAYER_TABLE_NAME
        } WHERE ${
            DatabaseConstants.PLAYER_DELETED_FLAG_COLUMN_NAME
        } = ${
            DatabaseConstants.DELETED_FLAG_FALSE
        }"
    )
    fun getAllPlayers(): Flow<List<PlayerEntity>>

    @Query(
        "SELECT * FROM ${
            DatabaseConstants.PLAYER_TABLE_NAME
        } WHERE ${
            DatabaseConstants.DEFAULT_ID_COLUMN_NAME
        } = :id"
    )
    suspend fun getPlayerById(id: Long): PlayerEntity?

    @Query(DatabaseQueries.PLAYERS_WITH_RESULTS_QUERY)
    fun getPlayersWithStats(): Flow<List<PlayerWithStatsDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayer(player: PlayerEntity): Long

    @Query(
        "UPDATE ${
            DatabaseConstants.PLAYER_TABLE_NAME
        } SET ${
            DatabaseConstants.PLAYER_DELETED_FLAG_COLUMN_NAME
        } = 1 WHERE ${
            DatabaseConstants.DEFAULT_ID_COLUMN_NAME
        } = :id"
    )
    suspend fun deletePlayer(id: Long)

    @Update
    suspend fun updatePlayer(player: PlayerEntity)
}