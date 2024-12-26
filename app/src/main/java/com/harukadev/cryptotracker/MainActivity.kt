package com.harukadev.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.cryptotracker.core.navigation.AdaptiveCoinListDetailPane
import com.harukadev.cryptotracker.core.presentation.util.ObserverAsEvents
import com.harukadev.cryptotracker.core.presentation.util.toString
import com.harukadev.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.harukadev.cryptotracker.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AdaptiveCoinListDetailPane(
                modifier = Modifier.padding(innerPadding)
            )
//            val viewModel: CoinListViewModel = koinViewModel()
//            val state by viewModel.state.collectAsStateWithLifecycle()
//
//            when {
//                state.selectedCoin != null -> {
//                    CoinDetailScreen(state, Modifier.padding(innerPadding))
//                }
//
//                else -> {
//                    CoinListScreen(
//                        state = state, modifier = Modifier.padding(innerPadding),
//                        onAction = viewModel::onAction
//                    )
//                }
//            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}