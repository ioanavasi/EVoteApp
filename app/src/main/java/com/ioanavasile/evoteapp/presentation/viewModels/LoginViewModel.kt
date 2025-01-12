package com.ioanavasile.evoteapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.UserCredentials
import com.ioanavasile.evoteapp.domain.repo.UserRepository
import com.ioanavasile.evoteapp.presentation.ui.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(cnp: String, password: String) {
        val userCredentials = UserCredentials(cnp, password)
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            delay(1000)
            when (val result = userRepo.login(userCredentials)) {
                is ApiResult.Success -> {
                    _loginState.value = LoginState.Success
                }

                is ApiResult.Error -> {
                    _loginState.value = LoginState.Error(result.message)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            when (val result = userRepo.logout()) {
                is ApiResult.Success -> {
                    _loginState.value = LoginState.Idle
                }

                is ApiResult.Error -> {
                    _loginState.value = LoginState.Error(result.message)
                }
            }
        }
    }
}