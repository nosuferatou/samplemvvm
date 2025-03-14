package com.example.mvvmproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmproject.data.local.DatabaseHelper
import com.example.mvvmproject.data.repository.UserRepository
import com.example.mvvmproject.databinding.ActivityLoginBinding
import com.example.mvvmproject.ui.home.HomeActivity
import com.example.mvvmproject.viewmodel.LoginViewModel
import com.example.mvvmproject.viewmodel.LoginViewModelFactory


class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = UserRepository(DatabaseHelper(this))
        val factory = LoginViewModelFactory(repository)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]


        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.editText?.text?.toString() ?: ""
            val password = binding.etPassword.editText?.text?.toString() ?: ""
            if (loginViewModel.loginUser(username, password)) {
                startActivity(Intent(this, HomeActivity::class.java))
                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong Username or Password!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}