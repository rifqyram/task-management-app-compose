package com.enigma.task_management.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.enigma.task_management.R

val fonts = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_bold, weight = FontWeight.Bold),
    Font(R.font.lato_light, weight = FontWeight.Light),
    Font(R.font.lato_thin, weight = FontWeight.Thin),
    Font(R.font.lato_black_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.lato_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.lato_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    h3 = TextStyle(
        fontFamily = fonts
    ),
    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)