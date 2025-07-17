package com.example.the7wonders.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.the7wonders.data.datasource.DatabaseConstants

@Entity(tableName = DatabaseConstants.PLAYER_TABLE_NAME)
data class PlayerEntity(
    val name: String,
    @PrimaryKey val id: Long? = null,
    val deleted: Boolean = false
)