package com.royals.muviassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.royals.muviassignment.R
import com.royals.muviassignment.listener.OnItemClickListener
import com.royals.muviassignment.model.Movie

class MovieAdapter(private val movies: List<Movie>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {



    class MovieViewHolder(view: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val title = view.findViewById<TextView>(R.id.movie_title)
        private val poster = view.findViewById<ImageView>(R.id.movie_poster)
        private lateinit var movie: Movie

        init {
            view.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            this.movie = movie
            title.text = movie.title
            Glide.with(poster.context)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .into(poster)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}

