package com.ncov.passph.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ncov.passph.PassPhApp
import com.ncov.passph.R
import com.ncov.passph.base.BaseActivity
import com.ncov.passph.core.network.TaskStatus
import com.ncov.passph.core.network.clients.TestClient
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject lateinit var testClient: TestClient

    override fun inject() {
        PassPhApp.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.test()
    }

    override fun observeChanges() {
        viewModel.testEvent.observe(this, Observer {
            when(it) {
                is TaskStatus.Success -> {
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }
                is TaskStatus.Failure -> {
                    Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
