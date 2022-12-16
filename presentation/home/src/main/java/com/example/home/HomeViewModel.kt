package com.example.home

import com.example.common.BaseViewModel
import com.example.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel(), HomeActionHandler {

    private val TAG = "HomeViewModel"

    private val _navigate: MutableSharedFlow<HomeNavigationAction> = MutableSharedFlow<HomeNavigationAction>()
    val navigate: SharedFlow<HomeNavigationAction> = _navigate.asSharedFlow()

    override fun onCategoryItemClicked(categoryId: Int) {
        baseViewModelScope.launch {
            _navigate.emit(HomeNavigationAction.NavigateToCategoryDetail(categoryId = categoryId))
        }
    }

    override fun onItemClicked(itemId: Int) {
        baseViewModelScope.launch {
            _navigate.emit(HomeNavigationAction.NavigateToDetail(itemId = itemId))
        }
    }
}