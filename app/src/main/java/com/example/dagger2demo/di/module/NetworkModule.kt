package com.example.dagger2demo.di.module

import com.example.dagger2demo.data.source.remote.GithubApi
import com.example.dagger2demo.data.source.remote.LoginRetrofitService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule {

//    // @Provides tell Dagger how to create instances of the type that this function
//    // returns (i.e. LoginRetrofitService).
//    // Function parameters are the dependencies of this type.
//    @Provides
//    fun provideLoginRetrofitService(): LoginRetrofitService {
//        // Whenever Dagger needs to provide an instance of type LoginRetrofitService,
//        // this code (the one inside the @Provides method) is run.
//        return Retrofit.Builder()
//            .baseUrl("https://example.com")
//            .build()
//            .create(LoginRetrofitService::class.java)
//    }

    @Singleton
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    // For using Moshi
    @Singleton
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideGithubApi(
        retrofit: Retrofit
    ): GithubApi = retrofit.create(GithubApi::class.java)

    // TODO: 上か下どっちかだけにする

    @Singleton
    @Provides
    fun provideLoginRetrofitService(
        retrofit: Retrofit
    ): LoginRetrofitService = retrofit.create(LoginRetrofitService::class.java)

    companion object {
        private const val BASE_URL = "https://api.github.com"
    }
}