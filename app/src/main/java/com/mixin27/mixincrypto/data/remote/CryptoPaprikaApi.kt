package com.mixin27.mixincrypto.data.remote

import com.mixin27.mixincrypto.data.remote.dto.CoinDetailDto
import com.mixin27.mixincrypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailDto
}