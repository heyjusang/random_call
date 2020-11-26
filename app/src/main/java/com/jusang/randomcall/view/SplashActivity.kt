package com.jusang.randomcall.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.jusang.randomcall.repository.LocalContactRepository
import com.jusang.randomcall.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<Nothing>() {
    // TODO : by viewmodels()
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = SplashViewModel(LocalContactRepository(this))
        viewModel.populateData()
        viewModel.result.observe(this, this)
    }

    override fun onDataLoaded(data: Nothing) {}

    override fun onTaskComplete() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}