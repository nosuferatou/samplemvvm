package com.example.mvvmproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmproject.data.repository.UserRepository

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    fun registerUser(username: String, password: String): Boolean {
        return repository.registerUser(username, password)
    }

    fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{8,}$"
        return password.matches(passwordPattern.toRegex())
    }
}