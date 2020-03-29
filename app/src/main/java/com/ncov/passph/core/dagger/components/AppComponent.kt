package com.ncov.passph.core.dagger.components

import com.ncov.passph.main.MainActivity
import com.ncov.passph.core.dagger.modules.NetworkModule
import com.ncov.passph.core.dagger.modules.TestApiModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class,
    TestApiModule::class
])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}