package com.harukadev.cryptotracker.crypto.domain

data class Coin(
    val id: Int,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapBr: Double,
    val priceBr: Double,
    val changePercent24Hr: Double
)