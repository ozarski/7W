package com.example.the7wonders.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.the7wonders.data.model.GameEntity
import com.example.the7wonders.data.model.PlayerEntity
import com.example.the7wonders.data.model.PlayerResultEntity

@Database(
    entities = [
        PlayerEntity::class,
        GameEntity::class,
        PlayerResultEntity::class
    ],
    version = 3,
)
abstract class GameDatabase : RoomDatabase() {

    abstract val playerDao: PlayerDao
    abstract val gameDao: GameDao
    abstract val playerResultDao: PlayerResultDao
}
