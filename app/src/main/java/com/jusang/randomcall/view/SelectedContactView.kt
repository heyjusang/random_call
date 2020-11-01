package com.jusang.randomcall.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.jusang.randomcall.R
import com.jusang.randomcall.databinding.ViewSelectedContactBinding

class SelectedContactView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout (context, attrs, defStyleAttr) {

    lateinit var binding: ViewSelectedContactBinding

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_selected_contact, this, true)
    }
}
