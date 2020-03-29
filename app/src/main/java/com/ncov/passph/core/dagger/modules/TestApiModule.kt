package com.ncov.passph.core.dagger.modules

import com.ncov.passph.core.network.clients.TestClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TestApiModule {

    @Provides
    @Singleton
    fun providesTestClient(retrofit: Retrofit) = retrofit.create(TestClient::class.java)
}