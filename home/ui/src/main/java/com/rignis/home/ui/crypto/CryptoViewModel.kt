package com.rignis.home.ui.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rignis.home.domain.CryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel
@Inject constructor(private val cryptoUseCase: CryptoUseCase) : ViewModel() {
    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    private val _messageByteArray: MutableStateFlow<ByteArray?> = MutableStateFlow(null)
    val message: StateFlow<String>
        get() = _message

    fun encrypt(message: String) {
        viewModelScope.launch {
            val output = cryptoUseCase.encrypt(message.encodeToByteArray())
            _messageByteArray.value = output
            _message.value = output.decodeToString()
        }
    }

    fun decrypt() {
        viewModelScope.launch {
            _message.update { currentMessage ->
                val currentByteArray = _messageByteArray.value
                if (currentByteArray != null) {
                    cryptoUseCase.decrypt(currentByteArray).decodeToString()
                } else {
                    currentMessage
                }
            }
        }
    }
}