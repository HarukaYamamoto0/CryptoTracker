package com.harukadev.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinsListState
import com.harukadev.cryptotracker.crypto.presentation.coin_list.components.previewCoinUi
import com.harukadev.cryptotracker.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CoinListScreen(
            state = CoinsListState(
                coins = (1..100).map {
                    previewCoinUi.copy(id = it.toString())
                }
            ),
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        HomeScreen()
    }
}