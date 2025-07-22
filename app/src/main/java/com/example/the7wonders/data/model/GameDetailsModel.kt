package com.example.the7wonders.data.model

import com.example.the7wonders.domain.model.PlayerResultModel

data class GameDetailsModel(val id: Long, val date: Long, val playerScores: List<PlayerResultModel>)
