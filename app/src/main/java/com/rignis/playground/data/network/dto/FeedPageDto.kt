package com.rignis.playground.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedPageDto(
    @SerialName("count")
    val count: Int,
    @SerialName("data")
    val data: List<FeedItemDto>
)