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

    override fun onAdultClick() {
        baseViewModelScope.launch {
            _navigate.emit(RegisterNavigationAction.NavigateToAdult)
        }
    }

    override fun onChildClick() {
        baseViewModelScope.launch {
            _navigate.emit(RegisterNavigationAction.NavigateToChild)
        }
    }

    override fun onEnterpriseClick() {
        baseViewModelScope.launch {
            _navigate.emit(RegisterNavigationAction.NavigateToEnterprise)
        }
    }
}