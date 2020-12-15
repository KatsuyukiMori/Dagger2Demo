package com.example.dagger2demo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dagger2demo.data.source.repository.UserRepository
import javax.inject.Inject

// @Inject tells Dagger how to create instances of LoginViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
}