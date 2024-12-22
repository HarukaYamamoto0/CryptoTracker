package com.harukadev.cryptotracker.di

import com.harukadev.cryptotracker.core.data.networking.HttpClientFactory
import com.harukadev.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.harukadev.cryptotracker.crypto.domain.CoinDataSource
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource) { bind<CoinDataSource>() }
    viewModelOf(::CoinListViewModel)
}