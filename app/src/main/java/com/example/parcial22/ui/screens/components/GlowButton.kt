package com.example.parcial22.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial22.ui.theme.PurpleNeon
import com.example.parcial22.ui.theme.GlowColor

@Composable
fun GlowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(20.dp, ambientColor = GlowColor, spotColor = GlowColor)
            .background(PurpleNeon)
            .clickable { onClick() }
            .padding(vertical = 14.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
