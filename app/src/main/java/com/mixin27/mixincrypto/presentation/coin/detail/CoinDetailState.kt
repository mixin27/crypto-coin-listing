package com.mixin27.mixincrypto.presentation.coin.detail

import com.mixin27.mixincrypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
