package com.harukadev.cryptotracker.crypto.domain

import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)
