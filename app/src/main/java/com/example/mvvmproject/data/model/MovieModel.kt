package com.example.mvvmproject.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val results: List<Movie>,
    val total_pages: Int = 1
) : Parcelable

@Parcelize
data class Movie(
    val id: Int,
    val poster_path: String,
    val title: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val overview: String,
    val vote_average: Float
) : Parcelable