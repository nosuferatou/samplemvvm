package com.example.mvvmproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmproject.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun loginUser(username: String, password: String): Boolean {
        return repository.loginUser(username, password)
    }
}