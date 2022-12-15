package com.example.register.info

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterInfoViewModel @Inject constructor(
) : BaseViewModel(), RegisterInfoActionHandler {

    private val TAG = "RegisterInfoViewModel"

    private val _navigate: MutableSharedFlow<RegisterInfoNavigationAction> = MutableSharedFlow<RegisterInfoNavigationAction>()
    val navigate: SharedFlow<RegisterInfoNavigationAction> = _navigate.asSharedFlow()

    val year: MutableStateFlow<String> = MutableStateFlow("")
    val email: MutableStateFlow<String> = MutableStateFlow("")
    val password: MutableStateFlow<String> = MutableStateFlow("")
    val passwordConfirm: MutableStateFlow<String> = MutableStateFlow("")

    override fun onRegisterClick() {
        baseViewModelScope.launch {
            if(year.value != "" && email.value != "" && password.value != "" && passwordConfirm.value != "") {
                if(password.value == passwordConfirm.value)
                    _navigate.emit(RegisterInfoNavigationAction.NavigateToSuccess(
                        email = email.value,
                        password = password.value
                    ))
                else
                    _navigate.emit(RegisterInfoNavigationAction.NavigateToPasswordNotMatched)
            } else {
                _navigate.emit(RegisterInfoNavigationAction.NavigateToEmpty)
            }
        }
    }
}