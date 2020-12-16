package com.example.dagger2demo.di.module

import com.example.dagger2demo.data.source.repository.UserRepository
import com.example.dagger2demo.data.source.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}