package com.jusang.randomcall.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jusang.randomcall.R
import com.jusang.randomcall.databinding.ActivityMainBinding
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.repository.LocalContactRepository
import com.jusang.randomcall.util.Constants
import com.jusang.randomcall.view.adapter.ContactListAdapter
import com.jusang.randomcall.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<ContactModel>>() {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(LocalContactRepository(this))
        binding.activity = this
        binding.viewModel?.result?.observe(this, this)
        // TODO : multiple viewmodel data

        adapter = ContactListAdapter()
        contact_recycler_view.layoutManager = LinearLayoutManager(this)
        contact_recycler_view.adapter = adapter

        initContacts()
    }

    private fun initContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    Constants.PERMISSION_REQUEST_READ_CONTACTS
                )
            }
            else {
                binding.viewModel?.loadContactList()
            }
        }
        // TODO case : VERSION < M
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Constants.PERMISSION_REQUEST_READ_CONTACTS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.viewModel?.loadContactList()
        }
    }

    fun selectButtonClicked(view: View) {
        binding.viewModel?.selectRandomContact()
    }

    override fun onTaskLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onDataLoaded(data: List<ContactModel>) {
        adapter.setContactList(data)
        adapter.notifyDataSetChanged()
    }

    override fun onTaskComplete() {
        progressBar.visibility = View.GONE
    }

    override fun onError(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}