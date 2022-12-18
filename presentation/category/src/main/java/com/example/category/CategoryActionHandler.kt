package com.example.category

interface CategoryActionHandler {
    fun onCategoryItemClicked(categoryImg: Int)

    fun onItemClicked(itemId: Int)
}