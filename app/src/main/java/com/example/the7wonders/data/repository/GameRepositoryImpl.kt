package com.example.the7wonders.data.repository

import com.example.the7wonders.data.DatabaseManager
import com.example.the7wonders.data.model.GameWithPlayerDetailsDto
import com.example.the7wonders.domain.model.GameDetailsModel
import com.example.the7wonders.domain.model.GameModel
import com.example.the7wonders.domain.model.toGameEntity
import com.example.the7wonders.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val databaseManager: DatabaseManager) :
    GameRepository {
    override suspend fun getGames(): Flow<List<GameModel>> {
        return databaseManager.getDatabase().gameDao.getGamesWithResults().map { dtos ->
            dtos.groupBy { it.gameID }
                .map { (gameId, rows) ->
                    GameModel(
                        id = gameId,
                        date = rows.first().date,
                        playerScores = rows.map { result ->
                            Pair(result.name, result.totalScore)
                        }
                    )
                }
        }
    }

    override suspend fun getGameDetails(id: Long): GameDetailsModel {
        val scores = databaseManager.getDatabase().gameDao.getGameDetails(id)
        return GameWithPlayerDetailsDto.toGameDetailsModel(scores)
    }

    override suspend fun addGame(game: GameModel): Long {
        return databaseManager.getDatabase().gameDao.addGame(game.toGameEntity())
    }

    override suspend fun deleteGame(game: GameModel) {
        return databaseManager.getDatabase().gameDao.deleteGame(game.toGameEntity())
    }
}