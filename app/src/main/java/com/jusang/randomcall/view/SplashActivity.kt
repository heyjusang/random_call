package com.jusang.randomcall.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jusang.randomcall.R
import com.jusang.randomcall.repository.LocalContactRepository
import com.jusang.randomcall.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<Nothing>() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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