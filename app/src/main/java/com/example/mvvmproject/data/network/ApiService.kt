package com.example.mvvmproject.data.network

import com.example.mvvmproject.data.model.MovieModel
import com.example.mvvmproject.data.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String = Constants.API_KEY): Response<MovieModel>
}