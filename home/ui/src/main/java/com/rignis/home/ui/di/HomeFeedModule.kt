package com.rignis.home.ui.di

import com.rignis.home.data.CryptoUseCaseImpl
import com.rignis.home.domain.CryptoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeFeedModule {
    @Binds
    fun bindCryptoUseCase(cryptoUseCaseImpl: CryptoUseCaseImpl): CryptoUseCase
}