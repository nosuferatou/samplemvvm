package com.example.mvvmproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.mvvmproject.data.local.DatabaseHelper
import com.example.mvvmproject.data.repository.UserRepository
import com.example.mvvmproject.databinding.ActivityRegisterBinding
import com.example.mvvmproject.viewmodel.RegisterViewModel
import com.example.mvvmproject.viewmodel.RegisterViewModelFactory


class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(UserRepository(DatabaseHelper(this)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.editText?.text?.toString() ?: ""
            val password = binding.etPassword.editText?.text?.toString() ?: ""

            if (!registerViewModel.isValidPassword(password)) {
                Toast.makeText(this, "Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
                }

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan password harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isRegistered = registerViewModel.registerUser(username, password)

            if (isRegistered) {
                Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_SHORT).show()
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, "Failed to Register!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}