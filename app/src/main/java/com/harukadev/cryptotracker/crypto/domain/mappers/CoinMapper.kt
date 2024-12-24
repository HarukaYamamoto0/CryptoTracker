package com.harukadev.cryptotracker.crypto.domain.mappers

import com.harukadev.cryptotracker.crypto.data.networking.dto.CoinDto
import com.harukadev.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.harukadev.cryptotracker.crypto.domain.Coin
import com.harukadev.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun CoinDto.toCoin(): Coin {
    return Coin(
        rank = rank,
        id = id,
        name = name,
        priceUsd = priceUsd,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd=priceUsd,
        dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}