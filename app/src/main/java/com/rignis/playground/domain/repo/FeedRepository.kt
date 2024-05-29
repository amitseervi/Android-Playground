package com.rignis.playground.domain.repo

interface FeedRepository {
    suspend fun getData(page: Int)
}