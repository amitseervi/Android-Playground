package com.rignis.playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rignis.playground.domain.repo.FeedRepository
import com.rignis.playground.state.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {
    private val _state =
        MutableStateFlow<MainScreenState>(MainScreenState.NoData(loading = true, error = null))
    val state: StateFlow<MainScreenState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.update {
                when (it) {
                    is MainScreenState.HasData -> it.copy(
                        loading = true,
                        error = null,
                        data = emptyList()
                    )

                    is MainScreenState.NoData -> it.copy(loading = true, error = null)
                }
            }
            delay(3000)
            val data = repository.getData(0)
            _state.emit(
                if (data.isEmpty()) {
                    MainScreenState.NoData(false, "No Data")
                } else {
                    MainScreenState.HasData(data = data)
                }
            )
        }
    }
}