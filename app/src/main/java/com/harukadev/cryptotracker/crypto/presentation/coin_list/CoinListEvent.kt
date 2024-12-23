package com.harukadev.cryptotracker.crypto.presentation.coin_list

import com.harukadev.cryptotracker.core.presentation.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}