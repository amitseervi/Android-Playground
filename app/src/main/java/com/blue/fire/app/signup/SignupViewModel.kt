package com.blue.fire.app.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.fire.authentication.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    fun onSignup(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.signupWithEmailAndPassword(name, email, password)
        }
    }
}