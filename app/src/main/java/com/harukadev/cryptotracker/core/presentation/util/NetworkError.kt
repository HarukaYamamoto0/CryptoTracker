package com.harukadev.cryptotracker.core.presentation.util

import com.harukadev.cryptotracker.core.domain.util.Error

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TO_MANY_REQUEST,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}