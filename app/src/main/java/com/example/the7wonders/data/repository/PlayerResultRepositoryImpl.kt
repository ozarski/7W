package com.example.the7wonders.data.repository

import com.example.the7wonders.data.datasource.PlayerResultDao
import com.example.the7wonders.domain.model.PlayerResultModel
import com.example.the7wonders.domain.model.toPlayerResultEntity
import com.example.the7wonders.domain.repository.PlayerResultRepository
import jakarta.inject.Inject

class PlayerResultRepositoryImpl @Inject constructor(private val playerResultDao: PlayerResultDao) :
    PlayerResultRepository {
    override suspend fun addPlayerResult(playerResultModel: PlayerResultModel, gameID: Long) {
        playerResultDao.addPlayerResults(playerResultModel.toPlayerResultEntity(gameID))
    }

    override suspend fun deletePlayerResult(playerResultModel: PlayerResultModel, gameID: Long) {
        playerResultDao.deletePlayerResults(playerResultModel.toPlayerResultEntity(gameID))
    }

}