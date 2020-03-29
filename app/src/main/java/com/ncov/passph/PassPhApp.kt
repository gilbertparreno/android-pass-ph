package com.ncov.passph

import android.app.Application
import com.ncov.passph.core.dagger.components.AppComponent
import com.ncov.passph.core.dagger.components.DaggerAppComponent
import com.ncov.passph.core.dagger.modules.NetworkModule
import com.ncov.passph.core.dagger.modules.TestApiModule
import okhttp3.logging.HttpLoggingInterceptor

class PassPhApp: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .networkModule(
                NetworkModule(
                    "http://api.pixelsideas.com/v1/",
                    HttpLoggingInterceptor.Level.BASIC
                )
            ).testApiModule(TestApiModule())
            .build()
    }
}