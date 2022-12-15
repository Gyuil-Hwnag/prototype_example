package com.example.register.info

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("createTypeText")
fun TextView.bindCreateTypeText(type: Int) {
    when(type) {
        1 -> this.text = context.getString(com.example.common.R.string.adult)
        2 -> this.text = context.getString(com.example.common.R.string.child)
        3 -> this.text = context.getString(com.example.common.R.string.enterprise)
    }
}