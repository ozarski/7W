package com.example.the7wonders.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.the7wonders.data.datasource.DatabaseConstants

@Entity(tableName = DatabaseConstants.GAME_TABLE_NAME)
data class GameEntity(
    @PrimaryKey val id: Long? = null,
    val date: Long
)
