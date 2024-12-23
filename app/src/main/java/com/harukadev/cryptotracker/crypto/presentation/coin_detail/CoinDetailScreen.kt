package com.harukadev.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.cryptotracker.R
import com.harukadev.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.harukadev.cryptotracker.crypto.presentation.coin_detail.components.InfoCard
import com.harukadev.cryptotracker.crypto.presentation.coin_list.CoinsListState
import com.harukadev.cryptotracker.crypto.presentation.coin_list.components.previewCoinUi
import com.harukadev.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.harukadev.cryptotracker.ui.theme.AppTheme
import com.harukadev.cryptotracker.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinsListState,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val colorContent = if (isSystemInDarkTheme()) Color.White else Color.Black

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val coin = state.selectedCoin

            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = colorContent
            )

            Text(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = colorContent
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    iconRes = R.drawable.stock,
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    descriptionRes = R.string.market_cap
                )

                InfoCard(
                    iconRes = R.drawable.dollar,
                    formattedText = "$ ${coin.priceUsd.formatted}",
                    descriptionRes = R.string.price
                )

                val absoluteChangeFormatted =
                    (coin.priceUsd.value * (coin.changePercent24Hr.value / 100)).toDisplayableNumber()
                val isPositive = coin.changePercent24Hr.value > 0.0
                val contentColor = if (isPositive) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else MaterialTheme.colorScheme.error


                InfoCard(
                    formattedText = "$ ${absoluteChangeFormatted.formatted}",
                    descriptionRes = R.string.change_last_24h,
                    iconRes = if (isPositive)
                        R.drawable.trending
                    else R.drawable.trending_down,
                    contentColor = contentColor
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
            CoinDetailScreen(
                state = CoinsListState(
                    selectedCoin = previewCoinUi
                )
            )
        }
    }
}