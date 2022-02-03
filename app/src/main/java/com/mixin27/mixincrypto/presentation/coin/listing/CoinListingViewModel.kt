package com.mixin27.mixincrypto.presentation.coin.listing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mixin27.mixincrypto.common.Resource
import com.mixin27.mixincrypto.domain.model.Coin
import com.mixin27.mixincrypto.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListingViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CoinListingState())
    val state: State<CoinListingState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinListingState(coins = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        CoinListingState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListingState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}