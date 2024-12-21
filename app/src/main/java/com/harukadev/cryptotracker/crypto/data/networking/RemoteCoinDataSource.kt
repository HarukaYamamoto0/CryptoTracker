package com.harukadev.cryptotracker.crypto.data.networking

import com.harukadev.cryptotracker.core.data.networking.constructUrl
import com.harukadev.cryptotracker.core.data.networking.safeCall
import com.harukadev.cryptotracker.core.domain.util.CoinDataSource
import com.harukadev.cryptotracker.core.domain.util.Result
import com.harukadev.cryptotracker.core.domain.util.map
import com.harukadev.cryptotracker.core.presentation.util.NetworkError
import com.harukadev.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.harukadev.cryptotracker.crypto.domain.Coin
import com.harukadev.cryptotracker.crypto.domain.mappers.toCoin
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}