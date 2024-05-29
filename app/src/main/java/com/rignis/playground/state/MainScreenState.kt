package com.rignis.playground.state

import com.rignis.playground.domain.model.FeedItem

sealed interface MainScreenState {
    val loading: Boolean
    val error: String?

    data class HasData(
        override val loading: Boolean = false,
        override val error: String? = null,
        val data: List<FeedItem>
    ) : MainScreenState

    data class NoData(
        override val loading: Boolean = false,
        override val error: String? = null
    ) : MainScreenState

}