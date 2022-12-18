package com.example.detail

import com.example.common.BaseViewModel
import com.example.model.Comments
import com.example.model.Community
import com.example.model.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : BaseViewModel(), DetailActionHandler {

    private val TAG = "DetailViewModel"

    private val _navigate: MutableSharedFlow<DetailNavigationAction> = MutableSharedFlow<DetailNavigationAction>()
    val navigate: SharedFlow<DetailNavigationAction> = _navigate.asSharedFlow()

    private val _detail: MutableStateFlow<Detail?> = MutableStateFlow<Detail?>(null)
    val detail: StateFlow<Detail?> = _detail.asStateFlow()

    private val isInvite: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)

    private val _counts: MutableStateFlow<Int> = MutableStateFlow(0)
    val counts: StateFlow<Int> = _counts.asStateFlow()

    private val _commentList: MutableStateFlow<List<Comments>> = MutableStateFlow<List<Comments>>(emptyList())
    val commentList: StateFlow<List<Comments>> = _commentList.asStateFlow()

    val newComments: MutableStateFlow<String> = MutableStateFlow<String>("")

    fun setCommentList(comments: List<Comments>) {
        baseViewModelScope.launch {
            _commentList.value = comments
        }
    }

    fun setDetail(detail: Detail) {
        baseViewModelScope.launch {
            _detail.value = detail
            _counts.value = detail.count
        }
    }

    override fun onInviteClicked() {
        baseViewModelScope.launch {
            if(!isInvite.value) {
                _counts.value = counts.value-1
                _navigate.emit(DetailNavigationAction.NavigateToInvite)
                isInvite.value = true
            } else {
                _navigate.emit(DetailNavigationAction.NavigateToOverCount)
            }
        }
    }
}