package com.example.the7wonders.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.the7wonders.data.model.GameEntity
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameEntity: GameEntity): Long

    @Delete
    suspend fun deleteGame(gameEntity: GameEntity)

}