package com.example.cryptocurrencydemo.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencydemo.presentation.coin_detail.components.Tag
import com.example.cryptocurrencydemo.presentation.coin_detail.components.TeamMemberItem
import com.example.cryptocurrencydemo.presentation.coin_list.ErrorText
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.screenDetailState.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = coin.name,
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.h2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.align(CenterVertically)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = coin.description,
                    style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Tag",
                    style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(15.dp))
                    // FlowRow comes from the dependency com.google.accompanist:accompanist-flowlayout:0.17.0
                    // which wraps the elements if they exceed the bounds.
                    FlowRow(mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp,
                    modifier = Modifier.fillMaxWidth()) {
                         coin.tags.forEach { tag ->
                             Tag(text = tag)
                         }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Team Members",
                    style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coin.team) { teamMember ->
                    TeamMemberItem(teamMember = teamMember, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    Divider()
                }

            }
            if(state.error.isNotEmpty()) {
                ErrorText(text = state.error, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(
                        Center
                    ))
            }
            if(state.isLoading) {
                CircularProgressIndicator()
            }
        }

    }
}