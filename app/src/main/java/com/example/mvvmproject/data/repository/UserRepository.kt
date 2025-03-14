package com.example.mvvmproject.data.repository

import com.example.mvvmproject.data.local.DatabaseHelper
import org.mindrot.jbcrypt.BCrypt

class UserRepository(private val dbHelper: DatabaseHelper) {

    fun registerUser(username: String, password: String): Boolean {
        // Check if user already exists
        if (dbHelper.getUserPassword(username) != null) {
            return false // User already exists
        }

        // Hash password
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())

        // Insert into database
        return dbHelper.insertUser(username, hashedPassword) > 0
    }

    fun loginUser(username: String, password: String): Boolean {
        val storedHashedPassword = dbHelper.getUserPassword(username)
        return storedHashedPassword?.let {
            BCrypt.checkpw(password, it) // Check if password is correct
        } ?: false // User not found
    }

    fun deleteUser(username: String): Boolean {
        return dbHelper.deleteUser(username) > 0
    }
}