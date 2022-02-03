package com.mixin27.mixincrypto.domain.repository

import com.mixin27.mixincrypto.data.remote.dto.CoinDetailDto
import com.mixin27.mixincrypto.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}