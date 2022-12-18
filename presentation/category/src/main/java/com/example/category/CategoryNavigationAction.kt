package com.example.category


sealed class CategoryNavigationAction {
    class NavigateToCategoryDetail(val categoryId: Int): CategoryNavigationAction()
    class NavigateToDetail(val itemId: Int): CategoryNavigationAction()
}