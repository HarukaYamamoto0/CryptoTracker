package com.harukadev.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.harukadev.cryptotracker.ui.theme.AppTheme
import com.harukadev.cryptotracker.ui.theme.greenBackground

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val isNegativeChange = change.value < 0.0

    val contentColor = if (isNegativeChange) {
        MaterialTheme.colorScheme.onErrorContainer
    } else MaterialTheme.colorScheme.onSecondary

    val backgroundColor = if (isNegativeChange) {
        MaterialTheme.colorScheme.errorContainer
    } else greenBackground

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(horizontal = 4.dp)
    ) {
        Icon(
            imageVector = if (isNegativeChange) {
                Icons.Default.KeyboardArrowDown
            } else Icons.Default.KeyboardArrowUp,
            contentDescription = "is negative change?",
            modifier = Modifier.size(20.dp),
            tint = contentColor
        )

        Text(
            text = "${change.formatted} %",
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    AppTheme {
        PriceChange(
            DisplayableNumber(
                value = 2.56,
                formatted = "2.56"
            )
        )
    }
}