package com.example.the7wonders.domain.model

import com.example.the7wonders.data.model.PlayerEntity

data class PlayerModel(
    val id: Long,
    val name: String,
    val wins: Int?,
    val games: Int?,
    val topScore: Int?,
    val avgPlacement: Double?
)

fun PlayerModel.toPlayerEntity() = PlayerEntity(
    id = id,
    name = name
)