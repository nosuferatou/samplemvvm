package com.example.mvvmproject.viewmodel

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.data.model.Movie
import com.example.mvvmproject.data.repository.MovieRepository
import kotlinx.coroutines.launch

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(space, space, space, space)
    }
}

class MovieViewModel (val repository: MovieRepository) : ViewModel() {
    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> get() = _nowPlayingMovies

    fun fetchNowPlayingMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val movieModel = repository.getNowPlayingMovies(apiKey) // ✅ Langsung MovieModel
                movieModel?.let {
                    _nowPlayingMovies.postValue(it.results) // ✅ Tidak perlu isSuccessful
                } ?: Log.e("MovieViewModel", "Error: MovieModel is null")
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Exception: ${e.message}")
            }
        }
    }
}