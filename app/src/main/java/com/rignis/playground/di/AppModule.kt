package com.rignis.playground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideJson(): Json {
        return Json {
            this.prettyPrint = true
        }
    }
}