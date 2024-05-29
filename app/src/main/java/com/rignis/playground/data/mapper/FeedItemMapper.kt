package com.rignis.playground.data.mapper

import com.rignis.playground.data.network.dto.FeedItemDto
import com.rignis.playground.domain.model.FeedItem

object FeedItemMapper {
    fun map(item: FeedItemDto): FeedItem {
        return FeedItem(
            id = item.id,
            image = item.image,
            title = item.title,
            description = item.description
        )
    }
}