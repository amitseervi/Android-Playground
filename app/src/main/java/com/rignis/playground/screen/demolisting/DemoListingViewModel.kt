package com.rignis.playground.screen.demolisting

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DemoListingViewModel @Inject constructor() : ViewModel() {
    val demoItems = MutableStateFlow(
        listOf(
            DemoItem(title = "Feed List", id = "home_list"),
            DemoItem(title = "Shader", id = "shader_text")
        )
    )
}

data class DemoItem(val title: String, val id: String)

