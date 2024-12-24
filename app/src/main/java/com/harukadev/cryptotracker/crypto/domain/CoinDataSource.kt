package com.harukadev.cryptotracker.crypto.domain

import com.harukadev.cryptotracker.core.domain.util.Result
import com.harukadev.cryptotracker.core.presentation.util.NetworkError
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}