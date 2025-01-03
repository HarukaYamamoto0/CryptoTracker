package com.harukadev.cryptotracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.harukadev.cryptotracker.R

val UbuntuMono = FontFamily(
    Font(
        resId = R.font.ubuntu_mono_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.ubuntu_mono_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.ubuntu_mono_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.ubuntu_mono_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
)
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Normal,
    ),
    headlineMedium = TextStyle(
        fontFamily = UbuntuMono,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)
