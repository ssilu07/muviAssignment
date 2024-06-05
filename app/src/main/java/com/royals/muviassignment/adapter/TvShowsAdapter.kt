package com.royals.muviassignment.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.royals.muviassignment.R
import com.royals.muviassignment.model.Show

class TvShowsAdapter(private val tvShows: List<Show>) : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    class TvShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.movie_title)
        private val poster = view.findViewById<ImageView>(R.id.movie_poster)

        fun bind(show: Show) {
            title.text = show.original_name
            Glide.with(poster.context)
                .load("https://image.tmdb.org/t/p/w500/${show.poster_path}")
                .into(poster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount() = tvShows.size
}
