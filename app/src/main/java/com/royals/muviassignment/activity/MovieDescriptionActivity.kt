package com.royals.muviassignment.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.royals.muviassignment.R
import com.royals.muviassignment.model.Movie
import com.royals.muviassignment.model.Result

class MovieDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_description)

        val bundle = intent.extras
        val movie = bundle?.getParcelable<Movie>("movie")
        val result = bundle?.getParcelable<Result>("result")

        val tvDescription = findViewById<TextView>(R.id.movie_overview)
        val moviePoster = findViewById<ImageView>(R.id.movie_poster)

        val posterPath = movie?.poster_path ?: result?.poster_path
        if (posterPath != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/$posterPath")
                .into(moviePoster)
        }

        val overview = movie?.overview ?: result?.overview
        if (overview != null) {
            tvDescription.text = overview
        }
    }
}
