package com.harukadev.cryptotracker.crypto.presentation.coin_detail

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
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
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val coin = state.selectedCoin

            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = colorContent,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = colorContent,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
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

                val isPositive = coin.changePercent24Hr.value > 0.0
                val contentColor = if (isPositive) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else MaterialTheme.colorScheme.error


                InfoCard(
                    formattedText = "${coin.changePercent24Hr.value.toDisplayableNumber().formatted}%",
                    descriptionRes = R.string.change_last_24h,
                    iconRes = if (isPositive)
                        R.drawable.trending
                    else R.drawable.trending_down,
                    contentColor = contentColor
                )
            }

            AnimatedVisibility(
                visible = coin.coinPriceHistory.isNotEmpty()
            ) {
                var selectedDataPoint by remember { mutableStateOf<DataPoint?>(null) }
                var labelWidth by remember { mutableFloatStateOf(0f) }

                var totalChartWidth by remember { mutableFloatStateOf(0f) }
                val amountOfVisibleDataPoints = if (labelWidth > 0) {
                    ((totalChartWidth - 2 * labelWidth) / labelWidth).toInt()
                } else 0

                val startIndex = (coin.coinPriceHistory.lastIndex - amountOfVisibleDataPoints)
                    .coerceAtLeast(0)

                LineChart(
                    dataPoints = coin.coinPriceHistory,
                    style = ChartStyle(
                        chartLineColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.secondary.copy(
                            alpha = 0.3f
                        ),
                        selectedColor = MaterialTheme.colorScheme.primary,
                        axisLinesThicknessPx = 5f,
                        labelFontSize = 14.sp,
                        minYLabelSpacing = 25.dp,
                        verticalPadding = 8.dp,
                        horizontalPadding = 8.dp,
                        xAxisLabelSpacing = 8.dp,
                        helperLineThicknessPx = 5f
                    ),
                    visibleDataPointsIndices = startIndex..coin.coinPriceHistory.lastIndex,
                    unit = "$",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .onSizeChanged { totalChartWidth = it.width.toFloat() },
                    selectedDataPoint = selectedDataPoint,
                    onSelectedDataPoint = {
                        selectedDataPoint = it
                        Log.e("Canvas", it.toString())
                    },
                    onXLabelWidthChange = { labelWidth = it }
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