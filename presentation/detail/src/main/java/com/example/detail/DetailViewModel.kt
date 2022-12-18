package com.example.detail

import com.example.common.BaseViewModel
import com.example.model.Comments
import com.example.model.Community
import com.example.model.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : BaseViewModel() {

    private val TAG = "DetailViewModel"

    private val _detail: MutableStateFlow<Detail?> = MutableStateFlow<Detail?>(null)
    val detail: StateFlow<Detail?> = _detail.asStateFlow()

    val counts: MutableStateFlow<Int> = MutableStateFlow(0)

    fun setDetail(detail: Detail) {
        baseViewModelScope.launch {
            _detail.value = detail
            counts.value = detail.count
        }
    }
}