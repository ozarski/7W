package com.example.the7wonders.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Player")
data class PlayerEntity(
    val name: String,
    @PrimaryKey val id: Long? = null,
    val deleted: Boolean = false
)