package com.example.the7wonders.domain.model

data class AddPlayerItem(
    val id: Long,
    val name: String,
    val isPlaying: Boolean,
    val ordinal: Int?
)