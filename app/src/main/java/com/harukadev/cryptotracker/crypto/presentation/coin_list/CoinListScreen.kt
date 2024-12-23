package com.harukadev.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.harukadev.cryptotracker.core.presentation.util.ObserverAsEvents
import com.harukadev.cryptotracker.core.presentation.util.toString
import com.harukadev.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.harukadev.cryptotracker.crypto.presentation.coin_list.components.previewCoinUi
import com.harukadev.cryptotracker.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    state: CoinsListState,
    onAction: (CoinListAction) -> Unit,
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
                    onClick = { onAction(CoinListAction.OnCoinClick(coinUi)) }
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
                ),
                onAction = {}
            )
        }
    }
}