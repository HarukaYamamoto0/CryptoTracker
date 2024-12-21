package com.harukadev.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val rank: Int,
    val id: String,
    val marketCapUsd: Double,
    val name: String,
    val priceUsd: Double,
    val symbol: String,
    val changePercent24Hr: Double,
)