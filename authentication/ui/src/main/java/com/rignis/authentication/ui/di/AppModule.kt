package com.rignis.authentication.ui.di

import com.rignis.authentication.data.repository.FirebaseAuthRepository
import com.rignis.authentication.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindAuthenticationRepository(firebaseAuthRepository: FirebaseAuthRepository): AuthRepository
}
