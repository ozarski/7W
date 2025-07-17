package com.example.the7wonders.data.repository

import com.example.the7wonders.data.datasource.GameDao
import com.example.the7wonders.domain.model.GameModel
import com.example.the7wonders.domain.model.toGameEntity
import com.example.the7wonders.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val gameDao: GameDao) : GameRepository {
    override suspend fun getGames(): Flow<List<GameModel>> {
        return gameDao.getGamesWithResults().map { dtos ->
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

    override suspend fun addGame(game: GameModel): Long {
        return gameDao.addGame(game.toGameEntity())
    }

    override suspend fun deleteGame(game: GameModel) {
        return gameDao.deleteGame(game.toGameEntity())
    }
}