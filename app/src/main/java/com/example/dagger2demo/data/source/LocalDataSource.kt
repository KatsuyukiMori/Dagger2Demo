package com.example.dagger2demo.data.source

import javax.inject.Inject

// @Inject lets Dagger know how to create instances of these objects
class UserLocalDataSource @Inject constructor() {
}
class UserRemoteDataSource @Inject constructor() {
}