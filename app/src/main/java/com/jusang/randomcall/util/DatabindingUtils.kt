package com.jusang.randomcall.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DatabindingUtils {

    @BindingAdapter("bind_image")
    @JvmStatic
    fun bindImage(imageView: ImageView, resId: Int) {
        Glide.with(imageView)
            .load(resId)
            .centerCrop()
            .into(imageView)
    }
}