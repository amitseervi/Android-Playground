package com.rignis.home.ui.di

import com.rignis.home.data.crypto.CryptoUseCaseImpl
import com.rignis.home.data.playground.PlayGroundUsecaseImpl
import com.rignis.home.domain.CryptoUseCase
import com.rignis.home.domain.PlayGroundUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeFeedModule {
    @Binds
    fun bindCryptoUseCase(cryptoUseCaseImpl: CryptoUseCaseImpl): CryptoUseCase

    @Binds
    fun bindPlayGroundUseCase(playGroundUsecaseImpl: PlayGroundUsecaseImpl): PlayGroundUsecase
}