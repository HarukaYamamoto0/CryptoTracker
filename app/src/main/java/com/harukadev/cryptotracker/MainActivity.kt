package com.harukadev.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.harukadev.cryptotracker.ui.theme.AppTheme
import org.koin.androidx.compose.KoinAndroidContext
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
fun HomeScreen(modifier: Modifier = Modifier) {
    AppTheme {
        KoinAndroidContext {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val viewModel: CoinListViewModel = koinViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()

                CoinListScreen(
                    state = state,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeScreen()
}