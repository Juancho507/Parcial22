package com.example.parcial22.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial22.ui.theme.PurpleNeon

@Composable
fun NeonText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: androidx.compose.ui.unit.TextUnit = 28.sp,
    glow: Boolean = false
) {

    val glowModifier = if (glow) {
        modifier.shadow(
            elevation = 20.dp,
            ambientColor = PurpleNeon,
            spotColor = PurpleNeon
        )
    } else modifier

    Text(
        text = text,
        modifier = glowModifier.padding(4.dp),
        color = PurpleNeon,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}

