package com.ioanavasile.evoteapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.User
import com.ioanavasile.evoteapp.domain.model.UserCredentials
import com.ioanavasile.evoteapp.domain.repo.UserRepository
import com.ioanavasile.evoteapp.presentation.ui.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<AuthState>(AuthState.Idle)
    val loginState: StateFlow<AuthState> = _loginState

    private val _registerState = MutableStateFlow<AuthState>(AuthState.Idle)
    val registerState: StateFlow<AuthState> = _registerState

    fun login(cnp: String, password: String) {
        val userCredentials = UserCredentials(cnp, password)
        viewModelScope.launch {
            _loginState.value = AuthState.Loading
            delay(1000)
            when (val result = userRepo.login(userCredentials)) {
                is ApiResult.Success -> {
                    _loginState.value = AuthState.Success
                }

                is ApiResult.Error -> {
                    _loginState.value = AuthState.Error(result.message)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loginState.value = AuthState.Loading
            when (val result = userRepo.logout()) {
                is ApiResult.Success -> {
                    _loginState.value = AuthState.Idle
                }

                is ApiResult.Error -> {
                    _loginState.value = AuthState.Error(result.message)
                }
            }
        }
    }

    fun register(
        cnp: String,
        email: String,
        firstName: String,
        lastName: String,
        password: String
    ) {
        val user = User(
            cnp = cnp,
            email = email,
            firstName = firstName,
            lastName = lastName,
            password = password
        )
        viewModelScope.launch {
            _registerState.value = AuthState.Loading
            delay(1000)
            when (val result = userRepo.register(user)) {
                is ApiResult.Success -> {
                    _registerState.value = AuthState.Success
                }

                is ApiResult.Error -> {
                    _registerState.value = AuthState.Error(result.message)
                }
            }
        }
    }
}