package com.example.dagger2demo.di.module

import com.example.dagger2demo.MyApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import javax.inject.Singleton

@Module
class MyApplicationModule(
    private val application: MyApplication
) {

    // アプリ全体で注入したいインスタンスを提供する→Applicationクラスのインスタンス
    @Singleton
    @Provides
    fun provideMyApplication(): MyApplication = application

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}