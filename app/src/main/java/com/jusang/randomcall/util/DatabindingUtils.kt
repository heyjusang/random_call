package com.jusang.randomcall.util

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.view.adapter.ContactListAdapter

object DatabindingUtils {

    @BindingAdapter("bind_contact_list")
    @JvmStatic
    fun bindContactList(recyclerView: RecyclerView, items:LiveData<List<ContactModel>>) {
        if (recyclerView.adapter == null) {
            recyclerView.run {
                layoutManager = LinearLayoutManager(recyclerView.context)
                adapter = ContactListAdapter()
            }
        }

        (recyclerView.adapter as ContactListAdapter).setContactList(items)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}