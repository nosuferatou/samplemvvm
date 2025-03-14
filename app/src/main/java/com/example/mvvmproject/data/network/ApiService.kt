package com.example.mvvmproject.data.network

import com.example.mvvmproject.data.model.MovieModel
import com.example.mvvmproject.data.utils.Constants
import com.example.mvvmproject.data.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String = Constants.API_KEY): Response<MovieModel>

    companion object {
        // Fungsi untuk membuat instance ApiService dengan Retrofit
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) // Ganti dengan base URL API kamu
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
