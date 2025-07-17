package com.example.the7wonders.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.the7wonders.data.datasource.DatabaseConstants
import com.example.the7wonders.domain.model.AddPlayerToGameModel

@Entity(tableName = DatabaseConstants.PLAYER_TABLE_NAME)
data class PlayerEntity(
    val name: String,
    @PrimaryKey val id: Long? = null,
    val deleted: Boolean = false
)

fun PlayerEntity.toDomainModel(): AddPlayerToGameModel {
    if (id == null) throw Exception("Player ID cannot be null")
    return AddPlayerToGameModel(
        id = id,
        name = name,
        isPlaying = false,
        ordinal = null
    )
}