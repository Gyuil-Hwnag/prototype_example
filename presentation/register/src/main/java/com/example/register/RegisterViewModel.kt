package com.example.register

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
) : BaseViewModel(), RegisterActionHandler {

    private val TAG = "RegisterViewModel"

    private val _navigate: MutableSharedFlow<RegisterNavigationAction> = MutableSharedFlow<RegisterNavigationAction>()
    val navigate: SharedFlow<RegisterNavigationAction> = _navigate.asSharedFlow()

    val id: MutableStateFlow<String> = MutableStateFlow<String>("")
    val password: MutableStateFlow<String> = MutableStateFlow<String>("")

    override fun onLoginClicked() {
        baseViewModelScope.launch {
            _navigate.emit(RegisterNavigationAction.NavigateToLogin(
                id = id.value,
                password = password.value
            ))
        }
    }

    override fun onRegisterClicked() {
        baseViewModelScope.launch {
            _navigate.emit(RegisterNavigationAction.NavigateToRegister)
        }
    }
}