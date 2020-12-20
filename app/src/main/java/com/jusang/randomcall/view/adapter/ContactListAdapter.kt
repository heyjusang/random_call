package com.jusang.randomcall.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jusang.randomcall.databinding.ItemContactBinding
import com.jusang.randomcall.entity.ContactEntity
import javax.inject.Inject

class ContactListAdapter @Inject constructor()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var contactList = mutableListOf<ContactEntity>()

    fun add(contactList: List<ContactEntity>) {
        this.contactList.addAll(contactList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactModel = contactList[position]

        val contactViewHolder = holder as ContactViewHolder

        contactViewHolder.bind(contactModel)
    }
}

class ContactViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(contactModel : ContactEntity) {
        binding.model = contactModel
    }
}
