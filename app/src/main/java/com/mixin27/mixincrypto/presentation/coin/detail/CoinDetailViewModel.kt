package com.mixin27.mixincrypto.presentation.coin.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mixin27.mixincrypto.common.Constants
import com.mixin27.mixincrypto.common.Resource
import com.mixin27.mixincrypto.domain.usecase.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoinDetail(it)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinDetailUseCase(coinId = coinId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = it.data)
                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}