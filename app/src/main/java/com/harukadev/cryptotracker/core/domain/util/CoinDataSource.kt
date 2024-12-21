package com.harukadev.cryptotracker.core.domain.util

import com.harukadev.cryptotracker.core.presentation.util.NetworkError
import com.harukadev.cryptotracker.crypto.domain.Coin

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}