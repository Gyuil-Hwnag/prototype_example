package com.example.common.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("countText")
fun TextView.bindCountText(count: Int) {
    this.text = count.toString()+"명 남았습니다!"
}