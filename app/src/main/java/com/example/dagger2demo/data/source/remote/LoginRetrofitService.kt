package com.example.dagger2demo.data.source.remote

import retrofit2.http.GET

interface LoginRetrofitService {

    // TODO: リクエスト未完成
    @GET("users")
    suspend fun getUsers(): List<String>
}