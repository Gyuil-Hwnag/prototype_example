package com.example.community.detail

import com.example.common.BaseViewModel
import com.example.model.Comments
import com.example.model.Community
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityDetailViewModel @Inject constructor(
) : BaseViewModel(), CommunityDetailActionHandler {

    private val TAG = "CommunityViewModel"

    private val _detail: MutableStateFlow<Community?> = MutableStateFlow<Community?>(null)
    val detail: StateFlow<Community?> = _detail.asStateFlow()

    private val _commentList: MutableStateFlow<List<Comments>> = MutableStateFlow<List<Comments>>(emptyList())
    val commentList: StateFlow<List<Comments>> = _commentList.asStateFlow()

    val newComments: MutableStateFlow<String> = MutableStateFlow<String>("")

    fun setDetail(community: Community) {
        baseViewModelScope.launch {
            _detail.value = community
        }
    }

    fun setCommentList(comments: List<Comments>) {
        baseViewModelScope.launch {
            _commentList.value = comments
        }
    }

    override fun onSendClicked() {
        baseViewModelScope.launch {
            val newList = mutableListOf<Comments>()

            val item = Comments(userName = "lyun", contents = newComments.value)
            newList.add(item)

            newList.addAll(commentList.value)

            _commentList.value = newList
            newComments.value = ""
        }
    }
}