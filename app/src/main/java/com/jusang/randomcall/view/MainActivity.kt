package com.jusang.randomcall.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jusang.randomcall.R
import com.jusang.randomcall.databinding.ActivityMainBinding
import com.jusang.randomcall.entity.ContactEntity
import com.jusang.randomcall.util.Constants
import com.jusang.randomcall.view.adapter.ContactListAdapter
import com.jusang.randomcall.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<List<ContactEntity>>() {
    private val viewModel: MainViewModel by viewModels()
    @Inject lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.activity = this

        contact_recycler_view.adapter = adapter

        viewModel.result.observe(this, this)
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
                viewModel.loadContactList()
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
            viewModel.loadContactList()
        }
    }

    fun selectButtonClicked(view: View) {
        viewModel.selectRandomContact()
    }

    override fun onTaskLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onDataLoaded(data: List<ContactEntity>) {
        adapter.add(data)
        progressBar.visibility = View.GONE
    }

    override fun onTaskComplete() {}

    override fun onError(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}