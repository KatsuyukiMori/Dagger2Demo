package com.example.dagger2demo.data.source.repository

import com.example.dagger2demo.data.source.UserLocalDataSource
import com.example.dagger2demo.data.source.UserRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @Inject lets Dagger know how to create instances of this object
 * このクラスのインスタンス生成時に引数にしているクラスもnewしてくれる
 * コンストラクタにて付けられるアノテーション
 *
 * @Singleton
 *
 */
@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {

}