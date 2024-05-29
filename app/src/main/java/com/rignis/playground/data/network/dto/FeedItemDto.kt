package com.rignis.playground.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedItemDto(
    @SerialName("title")
    val title: String,
    @SerialName("id")
    val id: String,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String
)