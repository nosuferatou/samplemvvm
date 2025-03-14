package com.example.mvvmproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.data.model.Movie
import com.example.mvvmproject.data.network.RetrofitClient
import com.example.mvvmproject.data.repository.MovieRepository
import com.example.mvvmproject.data.utils.Constants
import com.example.mvvmproject.databinding.ActivityHomeBinding
import com.example.mvvmproject.ui.adapter.NowPlayingAdapter
import com.example.mvvmproject.viewmodel.MovieViewModel
import com.example.mvvmproject.viewmodel.MovieViewModelFactory
import com.example.mvvmproject.viewmodel.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // fetch data from viewModel: MovieViewModel
        viewModel.fetchNowPlayingMovies(Constants.API_KEY)
        viewModel.nowPlayingMovies.observe(this) { movies ->
            if (movies != null) {
                setNowPlayingRv(movies)
            } else {
                Log.e("HomeActivity", "Error: nowPlayingMovies is null")
            }
        }

    }
    private fun setNowPlayingRv(movies: List<Movie>) {
        val nowPlayingRv = binding.rvNowPlaying
        nowPlayingRv.addItemDecoration(SpaceItemDecoration(10))
        nowPlayingRv.layoutManager = GridLayoutManager(this, 3)
        nowPlayingRv.adapter = NowPlayingAdapter(movies){ selectedMovie ->
//            startActivity(Intent(this, MovieDetailActivity::class.java).apply {
//                putExtra("MOVIE", selectedMovie)
            Toast.makeText(this, "Clicked: ${selectedMovie.title}", Toast.LENGTH_SHORT).show()
        }
        setRecyclerViewHeightForGrid(nowPlayingRv, 3)
    }

    private fun setRecyclerViewHeightForGrid(recyclerView: RecyclerView, spanCount: Int) {
        recyclerView.post {
            val adapter = recyclerView.adapter ?: return@post
            val itemCount = adapter.itemCount
            if (itemCount == 0) return@post

            val holder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(0))
            adapter.onBindViewHolder(holder, 0)

            holder.itemView.measure(
                View.MeasureSpec.makeMeasureSpec(recyclerView.width / spanCount, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.UNSPECIFIED
            )
            val itemHeight = holder.itemView.measuredHeight
            val rows = (itemCount + spanCount - 1) / spanCount
            val params = recyclerView.layoutParams
            params.height = (rows * itemHeight) + itemHeight
            recyclerView.layoutParams = params
            recyclerView.requestLayout()
        }
    }
}
