package com.example.mvvmproject.data.repository

import android.util.Log
import com.example.mvvmproject.data.model.MovieModel
import com.example.mvvmproject.data.network.ApiService


class MovieRepository(private val apiService:ApiService){
    suspend fun getNowPlayingMovies(apiKey: String): MovieModel? {
        return try {
            val response = apiService.getNowPlayingMovies(apiKey)
            if (response.isSuccessful) {
                response.body() // ✅ Kembalikan MovieModel langsung
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Exception: ${e.message}")
            null
        }
    }
}
