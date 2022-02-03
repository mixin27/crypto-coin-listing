package com.mixin27.mixincrypto.presentation.coin.listing

import com.mixin27.mixincrypto.domain.model.Coin

data class CoinListingState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
