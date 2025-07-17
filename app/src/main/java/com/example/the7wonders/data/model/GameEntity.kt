package com.example.the7wonders.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Game")
data class GameEntity(
    @PrimaryKey val id: Long? = null,
    val date: Long
)
