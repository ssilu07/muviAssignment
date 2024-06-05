package com.royals.muviassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.royals.muviassignment.R
import com.royals.muviassignment.model.Result

class UpcomingAdapter(private val nowPlayingList: List<Result>, private val listener: OnItemClickListener) : RecyclerView.Adapter<UpcomingAdapter.UpComingViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(result: Result)
    }

    class UpComingViewHolder(view: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val title = view.findViewById<TextView>(R.id.movie_title)
        private val poster = view.findViewById<ImageView>(R.id.movie_poster)
        private lateinit var result: Result

        init {
            view.setOnClickListener(this)
        }

        fun bind(nowPlaying: Result) {
            title.text = nowPlaying.title
            this.result = nowPlaying
            Glide.with(poster.context)
                .load("https://image.tmdb.org/t/p/w500/${nowPlaying.poster_path}")
                .into(poster)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return UpComingViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(nowPlayingList[position])
    }

    override fun getItemCount() = nowPlayingList.size
}
