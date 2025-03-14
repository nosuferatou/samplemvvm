package com.example.mvvmproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmproject.R
import com.example.mvvmproject.data.model.Movie
import com.example.mvvmproject.data.utils.Constants

class NowPlayingAdapter(
    private val nowPlayingList: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<NowPlayingAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView = view.findViewById(R.id.tvMovieTitle)
        val moviePoster: ImageView = view.findViewById(R.id.ivMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_nowplaying, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = nowPlayingList[position]
        holder.movieName.text = item.title

        holder.moviePoster.loadFromUrl(Constants.IMAGE_PATH + item.poster_path)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = nowPlayingList.size
}

// Move this function outside the class to make it an extension function
fun ImageView.loadFromUrl(imageUrl: String?) {
    val options = RequestOptions()
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .priority(Priority.IMMEDIATE)

    Glide.with(this.context)
        .load(imageUrl ?: "")
        .apply(options)
        .centerCrop()
        .into(this)
}
