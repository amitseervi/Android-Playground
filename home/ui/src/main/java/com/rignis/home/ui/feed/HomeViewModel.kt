/***
 * Copyright 2024 Amit Seervi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rignis.home.ui.feed

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
class HomeViewModel
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
                if (currentByteArray!=null) {
                    cryptoUseCase.decrypt(currentByteArray).decodeToString()
                } else {
                    currentMessage
                }
            }
        }
    }
}