package com.example.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("glide1000")
fun ImageView.bindGlide1000(uri: String) {
    Glide.with(context)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(1000))
        .into(this)
}

@BindingAdapter("glide300")
fun ImageView.bindGlide300(uri: String) {
    Glide.with(context)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(300))
        .into(this)
}

@BindingAdapter("glide100")
fun ImageView.bindGlide100(uri: String) {
    Glide.with(context)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(100))
        .into(this)
}

@BindingAdapter("glide")
fun ImageView.bindGlide(uri: String) {
    Glide.with(context)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(300))
        .into(this)
}

@BindingAdapter("glideInt")
fun ImageView.bindGlideInt(uri: Int) {
    Glide.with(context)
        .load(uri)
        .transform(FitCenter(), RoundedCorners(300))
        .into(this)
}

