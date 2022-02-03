package com.mixin27.mixincrypto.di

import com.mixin27.mixincrypto.common.Constants
import com.mixin27.mixincrypto.data.remote.CryptoPaprikaApi
import com.mixin27.mixincrypto.data.repository.CoinRepositoryImpl
import com.mixin27.mixincrypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): CryptoPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CryptoPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}