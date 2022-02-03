package com.mixin27.mixincrypto.presentation

sealed class Screen(val route: String) {
    object CoinListingScreen: Screen("coin_listing_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}
