package com.example.category

import com.example.common.BaseViewModel
import com.example.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
) : BaseViewModel(), CategoryActionHandler {

    private val TAG = "CategoryViewModel"

    private val _navigate: MutableSharedFlow<CategoryNavigationAction> = MutableSharedFlow<CategoryNavigationAction>()
    val navigate: SharedFlow<CategoryNavigationAction> = _navigate.asSharedFlow()

    private val _titleImg: MutableStateFlow<Int> = MutableStateFlow(R.drawable.key)
    val titleImg: StateFlow<Int> = _titleImg.asStateFlow()

    fun setTitleImg(image: Int) {
        baseViewModelScope.launch {
            _titleImg.value = image
        }
    }

    override fun onCategoryItemClicked(categoryImg: Int) {
        baseViewModelScope.launch {
            _titleImg.value = categoryImg
        }
    }

    override fun onItemClicked(itemId: Int) {
        baseViewModelScope.launch {
            _navigate.emit(CategoryNavigationAction.NavigateToDetail(itemId = itemId))
        }
    }
}