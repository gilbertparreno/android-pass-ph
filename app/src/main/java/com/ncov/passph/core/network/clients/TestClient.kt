package com.ncov.passph.core.network.clients

import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TestClient {

    @POST("login")
    @FormUrlEncoded
    suspend fun test(
        @Field("username") userName: String,
        @Field("password") password: String
    ) : Any
}