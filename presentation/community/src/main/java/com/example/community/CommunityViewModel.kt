package com.example.community

import com.example.common.BaseViewModel
import com.example.model.Community
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
) : BaseViewModel(), CommunityActionHandler {

    private val TAG = "CommunityViewModel"

    private val _navigate: MutableSharedFlow<Community> = MutableSharedFlow<Community>()
    val navigate: SharedFlow<Community> = _navigate.asSharedFlow()

    override fun onCommunityItemClicked(community: Community) {
        baseViewModelScope.launch {
            _navigate.emit(community)
        }
    }
}