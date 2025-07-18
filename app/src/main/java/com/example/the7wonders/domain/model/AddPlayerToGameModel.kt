package com.example.the7wonders.domain.model

data class AddPlayerToGameModel(
    val id: Long,
    val name: String,
    val isPlaying: Boolean,
    val ordinal: Int?
)