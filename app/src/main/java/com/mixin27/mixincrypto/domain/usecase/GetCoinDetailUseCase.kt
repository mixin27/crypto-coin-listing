package com.mixin27.mixincrypto.domain.usecase

import com.mixin27.mixincrypto.common.Resource
import com.mixin27.mixincrypto.data.remote.dto.toDomain
import com.mixin27.mixincrypto.domain.model.CoinDetail
import com.mixin27.mixincrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())

            val coin = repository.getCoinById(coinId).toDomain()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your internet connection."))
        }
    }
}