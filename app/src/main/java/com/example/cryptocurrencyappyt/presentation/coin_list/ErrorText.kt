package com.example.cryptocurrencydemo.presentation.coin_list

import android.graphics.Color
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ErrorText(
    text: String,
    modifier: Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colors.error,
        textAlign = textAlign
    )
}