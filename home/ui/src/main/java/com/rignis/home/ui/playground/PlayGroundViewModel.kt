package com.rignis.home.ui.playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rignis.home.domain.PlayGroundUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayGroundViewModel @Inject constructor(private val playGroundUsecase: PlayGroundUsecase) :
    ViewModel() {
    val progress: StateFlow<Int>
        get() = playGroundUsecase.currentProgress

    fun onButtonPress() {
        viewModelScope.launch {
            playGroundUsecase.playAround()
        }
    }
}