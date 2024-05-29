package com.rignis.playground.data.repo

import android.app.Application
import android.content.Context
import com.rignis.playground.data.mapper.FeedItemMapper
import com.rignis.playground.data.network.dto.FeedPageDto
import com.rignis.playground.domain.model.FeedItem
import com.rignis.playground.domain.repo.FeedRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class HomeFeedRepositoryImpl @Inject constructor(
    private val context: Application,
    private val json: Json
) : FeedRepository {
    override suspend fun getData(page: Int): List<FeedItem> {
        return readJsonFile(page).data.map { FeedItemMapper.map(it) }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun readJsonFile(pageIndex: Int): FeedPageDto = withContext(Dispatchers.IO) {
        return@withContext json.decodeFromStream<FeedPageDto>(context.assets.open("page_$pageIndex.json"))
    }
}