package com.jusang.randomcall.view.adapter

import android.app.Application
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

import com.jusang.randomcall.databinding.ItemContactBinding
import com.jusang.randomcall.model.ContactModel


class ContactListAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var contactList: LiveData<List<ContactModel>>

    fun setContactList(contactList: LiveData<List<ContactModel>>) {
        this.contactList = contactList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.value!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactModel = contactList.value!![position]

        val contactViewHolder = holder as ContactViewHolder

        contactViewHolder.bind(contactModel)
    }
}

class ContactViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(contactModel : ContactModel) {
        binding.model = contactModel
    }
}
