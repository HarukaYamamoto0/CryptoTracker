package com.harukadev.cryptotracker.core.navigation

import android.widget.ListAdapter
import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.cryptotracker.core.presentation.util.ObserverAsEvents
import com.harukadev.cryptotracker.core.presentation.util.toString
import com.harukadev.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListAction
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserverAsEvents(viewModel.events) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val coroutineScope = rememberCoroutineScope()

    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state = state,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when (action) {
                            is CoinListAction.OnCoinClick -> {
                                coroutineScope.launch {
                                    navigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.Detail
                                    )
                                }
                            }

                            CoinListAction.OnRefresh -> {}
                        }
                    }
                )
            }
        },
        detailPane = {
            CoinDetailScreen(state = state)
        },
        modifier = modifier
    )
}