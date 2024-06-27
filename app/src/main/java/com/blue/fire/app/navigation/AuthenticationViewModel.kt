package com.blue.fire.app.navigation

import androidx.lifecycle.ViewModel
import com.blue.fire.authentication.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    val currentUser = authRepository.currentUser
}