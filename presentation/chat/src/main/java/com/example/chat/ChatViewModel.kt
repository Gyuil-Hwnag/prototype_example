package com.example.chat

import com.example.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
) : BaseViewModel(), ChatActionHandler {

    private val TAG = "HomeViewModel"

    private val _navigate: MutableSharedFlow<ChatNavigationAction> = MutableSharedFlow<ChatNavigationAction>()
    val navigate: SharedFlow<ChatNavigationAction> = _navigate.asSharedFlow()

    override fun onChatItemClicked() {
        baseViewModelScope.launch {
            _navigate.emit(ChatNavigationAction.NavigateToChatDetail)
        }
    }
}