package com.mixin27.mixincrypto.data.repository

import com.mixin27.mixincrypto.data.remote.CryptoPaprikaApi
import com.mixin27.mixincrypto.data.remote.dto.CoinDetailDto
import com.mixin27.mixincrypto.data.remote.dto.CoinDto
import com.mixin27.mixincrypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CryptoPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}