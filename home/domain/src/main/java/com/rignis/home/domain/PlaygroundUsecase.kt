package com.rignis.home.domain

import kotlinx.coroutines.flow.StateFlow

interface PlayGroundUsecase {
    fun playAround()
    val currentProgress: StateFlow<Int>
}