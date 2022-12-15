package com.example.launch

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
) : BaseViewModel(), LaunchActionHandler {

    private val TAG = "LaunchViewModel"

    private val _navigate: MutableSharedFlow<LaunchNavigationAction> = MutableSharedFlow<LaunchNavigationAction>()
    val navigate: SharedFlow<LaunchNavigationAction> = _navigate.asSharedFlow()

    val id: MutableStateFlow<String> = MutableStateFlow<String>("")
    val password: MutableStateFlow<String> = MutableStateFlow<String>("")

    override fun onLoginClicked() {
        baseViewModelScope.launch {
            if(id.value == "" || password.value == "") {
                _navigate.emit(LaunchNavigationAction.NavigateToEmpty)
            } else {
                _navigate.emit(LaunchNavigationAction.NavigateToLogin(id = id.value, password = password.value))
            }
        }
    }

    override fun onRegisterClicked() {
        baseViewModelScope.launch {
            _navigate.emit(LaunchNavigationAction.NavigateToRegister)
        }
    }
}