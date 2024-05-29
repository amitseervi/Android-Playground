package com.rignis.playground.di

import com.rignis.playground.data.repo.HomeFeedRepositoryImpl
import com.rignis.playground.domain.repo.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindingModule {

    @Binds
    abstract fun bindHomeFeedRepository(homeFeedRepositoryImpl: HomeFeedRepositoryImpl): FeedRepository
}