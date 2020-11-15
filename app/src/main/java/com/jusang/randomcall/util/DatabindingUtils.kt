package com.jusang.randomcall.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.model.State
import com.jusang.randomcall.view.adapter.ContactListAdapter

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