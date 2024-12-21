package com.harukadev.cryptotracker.crypto.domain

import com.harukadev.cryptotracker.core.domain.util.Result
import com.harukadev.cryptotracker.core.presentation.util.NetworkError

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}