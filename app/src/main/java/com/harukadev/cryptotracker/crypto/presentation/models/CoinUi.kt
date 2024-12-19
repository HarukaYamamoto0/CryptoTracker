package com.harukadev.cryptotracker.crypto.presentation.models

import android.icu.number.NumberFormatter
import androidx.annotation.DrawableRes
import com.harukadev.cryptotracker.crypto.domain.Coin
import com.harukadev.cryptotracker.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: Int,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapBr: DisplayableNumber,
    val priceBr: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapBr = marketCapBr.toDisplayableNumber(),
        priceBr = priceBr.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}