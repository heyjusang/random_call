package com.jusang.randomcall.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.jusang.randomcall.R
import com.jusang.randomcall.databinding.ActivityMainBinding
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.util.Constants
import com.jusang.randomcall.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(application)

        observeViewModel()
        checkPermission()
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), Constants.PERMISSION_REQUEST_READ_CONTACTS)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Constants.PERMISSION_REQUEST_READ_CONTACTS) {
            binding.viewModel?.onRequestReadContactsPermissionsResult(grantResults[0])
        }
    }

    private fun observeViewModel() {
        binding.viewModel?.getContactList()?.observe(this, Observer {
            contact_recycler_view.adapter?.notifyDataSetChanged()
        })
    }
}