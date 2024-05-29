package com.rignis.playground.domain.repo

import com.rignis.playground.domain.model.FeedItem

interface FeedRepository {
    suspend fun getData(page: Int): List<FeedItem>
}