package com.example.cryptocurrencydemo.presentation.coin_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencydemo.common.Constants
import com.example.cryptocurrencydemo.common.Resource
import com.example.cryptocurrencydemo.domain.repository.CoinRepository
import com.example.cryptocurrencydemo.domain.use_cases.get_coin_detail.GetCoinDetailUseCase
import com.example.cryptocurrencydemo.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinDetailUseCase: GetCoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _screenDetailState = mutableStateOf(CoinDetailState())
    val screenDetailState = _screenDetailState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoinDetails(it)
        }
    }

    private fun getCoinDetails(coinId: String) {
        coinDetailUseCase.invoke(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _screenDetailState.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _screenDetailState.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _screenDetailState.value = CoinDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}