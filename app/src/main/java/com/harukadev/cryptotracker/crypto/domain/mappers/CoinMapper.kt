package com.harukadev.cryptotracker.crypto.domain.mappers

import com.harukadev.cryptotracker.crypto.data.networking.dto.CoinDto
import com.harukadev.cryptotracker.crypto.domain.Coin

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