package com.example.cryptocurrencydemo.presentation.coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencydemo.presentation.ui.theme.ColorPrimary

@Composable
fun Tag(
    text: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(100.dp),
                color = MaterialTheme.colors.primary
            )
            .padding(10.dp)
    ) {
        Text(text = text,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body2)
    }
}