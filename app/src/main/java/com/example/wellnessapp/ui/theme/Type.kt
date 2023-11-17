package com.example.wellnessapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wellnessapp.R

val Jaldi = FontFamily(
    Font(R.font.jaldi_regular),
    Font(R.font.jaldi_bold, FontWeight.Bold)
)

val Jost = FontFamily(
    Font(R.font.jost_regular)
)
// Set of Material typography styles to start with
val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = Jaldi,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Jaldi,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Jost,
        fontSize = 18.sp
    )
)