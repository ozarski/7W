package com.example.the7wonders.domain.repository

import com.example.the7wonders.domain.model.PlayerResultModel

interface PlayerResultRepository {

    suspend fun addPlayerResult(playerResultModel: PlayerResultModel, gameID: Long)

    suspend fun deletePlayerResult(playerResultModel: PlayerResultModel, gameID: Long)

    suspend fun deletePlayerResultsForGame(gameID: Long)
}