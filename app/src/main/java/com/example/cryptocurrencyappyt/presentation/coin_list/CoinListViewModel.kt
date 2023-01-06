package com.example.cryptocurrencydemo.presentation.coin_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencydemo.common.Resource
import com.example.cryptocurrencydemo.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    val coinUseCase: GetCoinUseCase
) : ViewModel() {
    private val _screenState = mutableStateOf(CoinListState())
    val screenState = _screenState

    init {
        getCoins()
    }

    private fun getCoins() {
        coinUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _screenState.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    CoinListState(error = result.message ?: "Unexpeted Error occurred")
                }
                is Resource.Loading -> {
                    CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}