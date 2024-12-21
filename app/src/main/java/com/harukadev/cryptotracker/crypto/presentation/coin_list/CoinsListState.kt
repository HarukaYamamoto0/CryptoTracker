package com.harukadev.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.harukadev.cryptotracker.crypto.presentation.models.CoinUi

@Immutable
data class  CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)