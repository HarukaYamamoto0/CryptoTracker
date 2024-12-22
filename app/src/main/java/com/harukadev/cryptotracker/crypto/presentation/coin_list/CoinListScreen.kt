package com.harukadev.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.harukadev.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.harukadev.cryptotracker.crypto.presentation.coin_list.components.previewCoinUi
import com.harukadev.cryptotracker.ui.theme.AppTheme

@Composable
fun CoinListScreen(
    state: CoinsListState,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {}
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinsListScreenPreview(modifier: Modifier = Modifier) {
    AppTheme {
        MaterialTheme {
            CoinListScreen(
                state = CoinsListState(
                    coins = (1..100).map {
                        previewCoinUi.copy(id = it.toString())
                    }
                )
            )
        }
    }
}