package com.example.home

interface HomeActionHandler {
    fun onCategoryItemClicked(categoryId: Int)

    fun onItemClicked(itemId: Int)
}