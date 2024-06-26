package com.blue.fire.app.di

import com.blue.fire.authentication.repository.AuthRepository
import com.blue.fire.data.authentication.firebase.FirebaseAuthRepository
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