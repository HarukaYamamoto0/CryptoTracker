package com.harukadev.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.cryptotracker.crypto.domain.Coin
import com.harukadev.cryptotracker.crypto.presentation.models.CoinUi
import com.harukadev.cryptotracker.crypto.presentation.models.toCoinUi
import com.harukadev.cryptotracker.ui.theme.AppTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else Color.Black

    Row(
        modifier = modifier
            .padding(bottom = 1.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            modifier = Modifier.size(85.dp),
            imageVector = ImageVector.vectorResource(coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary
        )

        Column(Modifier.fillMaxWidth().weight(1f)) {
            Text(
                text = coinUi.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Text(
                text = coinUi.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$ ${coinUi.marketCapUsd.formatted}",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )

            Spacer(Modifier.height(8.dp))

            PriceChange(coinUi.changePercent24Hr)
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListItemPreview(modifier: Modifier = Modifier) {
    AppTheme {
        CoinListItem(
            coinUi = previewCoinUi,
            onClick = {}
        )
    }
}


internal val previewCoinUi = Coin(
    id = "bitcoin",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = 1.9018460867853474E12,
    priceUsd = 96052.8666240108,
    changePercent24Hr = -1.0296288162086142
).toCoinUi()