package com.example.chat.detail

import com.example.common.BaseViewModel
import com.example.model.ChatDetail
import com.example.model.Comments
import com.example.model.Community
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
) : BaseViewModel(), ChatDetailActionHandler {

    private val TAG = "ChatDetailViewModel"

    private val _commentList: MutableStateFlow<List<ChatDetail>> = MutableStateFlow<List<ChatDetail>>(emptyList())
    val commentList: StateFlow<List<ChatDetail>> = _commentList.asStateFlow()

    val newComments: MutableStateFlow<String> = MutableStateFlow<String>("")

    fun setCommentList(comments: List<ChatDetail>) {
        baseViewModelScope.launch {
            _commentList.value = comments
        }
    }

    override fun onSendClicked() {
        baseViewModelScope.launch {
            val newList = mutableListOf<ChatDetail>()

            newList.addAll(commentList.value)

            val item = ChatDetail(
                chatId = 12,
                chatType = 1,
                userName = "lyun",
                chatContents = newComments.value
            )
            newList.add(item)

            _commentList.value = newList
            newComments.value = ""
        }
    }
}