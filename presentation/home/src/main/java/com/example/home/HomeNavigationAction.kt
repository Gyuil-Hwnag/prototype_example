package com.example.home


sealed class HomeNavigationAction {
    class NavigateToCategoryDetail(val categoryId: Int): HomeNavigationAction()
    class NavigateToDetail(val itemId: Int): HomeNavigationAction()
}