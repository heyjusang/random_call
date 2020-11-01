package com.jusang.randomcall.view

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.jusang.randomcall.R
import com.jusang.randomcall.databinding.ActivityMainBinding
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.repository.LocalContactRepository
import com.jusang.randomcall.util.Constants
import com.jusang.randomcall.util.DatabindingUtils
import com.jusang.randomcall.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(LocalContactRepository(this))
        binding.activity = this

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
                observeViewModel()
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
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        binding.viewModel?.getContactList()?.observe(this, Observer {
            contact_recycler_view.adapter?.notifyDataSetChanged()
        })

        binding.viewModel?.getRandomContact()?.observe(this, Observer {
            // TODO : remove observer using viewmodel in SelectContactView
            DatabindingUtils.bindSelectedContact(selected_contact_layout, (binding.viewModel as MainViewModel).getRandomContact())
        })
    }

    fun selectButtonClicked(view: View) {
        binding.viewModel?.selectRandomContact()
    }
}