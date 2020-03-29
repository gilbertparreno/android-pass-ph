package com.ncov.passph.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ncov.passph.core.utils.ViewModelUtils
import javax.inject.Inject

abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {

    @Inject lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        viewModel = ViewModelProvider(viewModelStore, ViewModelUtils.createFor(viewModel)).get(viewModel::class.java)
        observeChanges()
    }

    protected abstract fun inject()
    protected abstract fun observeChanges()
}