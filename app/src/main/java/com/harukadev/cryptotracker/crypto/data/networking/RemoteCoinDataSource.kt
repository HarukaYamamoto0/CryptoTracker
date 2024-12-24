package com.harukadev.cryptotracker.crypto.data.networking

import com.harukadev.cryptotracker.core.data.networking.constructUrl
import com.harukadev.cryptotracker.core.data.networking.safeCall
import com.harukadev.cryptotracker.crypto.domain.CoinDataSource
import com.harukadev.cryptotracker.core.domain.util.Result
import com.harukadev.cryptotracker.core.domain.util.map
import com.harukadev.cryptotracker.core.presentation.util.NetworkError
import com.harukadev.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.harukadev.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.harukadev.cryptotracker.crypto.domain.Coin
import com.harukadev.cryptotracker.crypto.domain.CoinPrice
import com.harukadev.cryptotracker.crypto.domain.mappers.toCoin
import com.harukadev.cryptotracker.crypto.domain.mappers.toCoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMilliseconds = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMilliseconds = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMilliseconds)
                parameter("end", endMilliseconds)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}