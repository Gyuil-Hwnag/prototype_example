package com.example.my

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
) : BaseViewModel(), MyActionHandler {

    private val TAG = "MyViewModel"

    val userImg: MutableStateFlow<Int> = MutableStateFlow(R.drawable.key)

    private val _navigate: MutableSharedFlow<MyNavigationAction> = MutableSharedFlow<MyNavigationAction>()
    val navigate: SharedFlow<MyNavigationAction> = _navigate.asSharedFlow()

    override fun onLogoutClicked() {
        baseViewModelScope.launch {
            _navigate.emit(MyNavigationAction.NavigateToLogout)
        }
    }

    override fun onDeleteClicked() {
        baseViewModelScope.launch {
            _navigate.emit(MyNavigationAction.NavigateToDelete)
        }
    }

    override fun onSendClicked() {
        baseViewModelScope.launch {
            _navigate.emit(MyNavigationAction.NavigateToSend)
        }
    }
}