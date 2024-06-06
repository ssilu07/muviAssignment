package com.royals.muviassignment.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.royals.muviassignment.R
import com.royals.muviassignment.activity.MovieDescriptionActivity
import com.royals.muviassignment.adapter.MovieAdapter
import com.royals.muviassignment.adapter.TvShowsAdapter
import com.royals.muviassignment.adapter.UpcomingAdapter
import com.royals.muviassignment.listener.OnItemClickListener
import com.royals.muviassignment.model.Movie
import com.royals.muviassignment.model.Result
import com.royals.muviassignment.viewmodel.MovieViewModel
import com.royals.muviassignment.viewmodel.TvShowsViewModel
import com.royals.muviassignment.viewmodel.UpComingViewModel

class HomeFragment : Fragment(R.layout.fragment_home),OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewUpComing: RecyclerView
    private lateinit var tvShow: RecyclerView
    private lateinit var viewModel: MovieViewModel
    private lateinit var upComingViewModel: UpComingViewModel
    private lateinit var tvShowViewModel: TvShowsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val horizontalLinearLayout: LinearLayout = view.findViewById(R.id.horizontalLinearLayout)
        val itemsList = listOf("All", "Live", "Documentaries", "Drama", "Comedy")
        var selectedButton: Button? = null
        for (item in itemsList) {
            val itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_layout, horizontalLinearLayout, false)
            val itemTextView: Button = itemView.findViewById(R.id.itemText)
            itemTextView.text = item
            itemTextView.setOnClickListener {
                // Reset the background color of the previously selected button if there was any
                selectedButton?.setBackgroundColor(Color.TRANSPARENT)

                // Set the background color of the clicked button
                context?.let { it1 -> ContextCompat.getColor(it1, R.color.Red) }
                    ?.let { it2 -> itemTextView.setBackgroundColor(it2) }

                // Keep track of the selected button
                selectedButton = itemTextView

            }
            horizontalLinearLayout.addView(itemView)
        }
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerViewUpComing = view.findViewById(R.id.recyclerViewUpComing)
        tvShow = view.findViewById(R.id.tvShow_rv)

        //to set the recyclerview
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUpComing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvShow.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //viewmodel provider to observe the viewmodel class
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            recyclerView.adapter = MovieAdapter(movies, object : OnItemClickListener {
                override fun onItemClick(movie: Movie) {
                    val bundle = Bundle()
                    bundle.putParcelable("movie", movie)
                    val intent = Intent(requireContext(), MovieDescriptionActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)

                }

                override fun onUpcomingItemClick(result: Result) {
                    TODO("Not yet implemented")
                }
            })
        }

        upComingViewModel = ViewModelProvider(this)[UpComingViewModel::class.java]
        upComingViewModel.movies.observe(viewLifecycleOwner) { result ->
            recyclerViewUpComing.adapter = UpcomingAdapter(result, object : OnItemClickListener {
                override fun onItemClick(movie: Movie) {
                    TODO("Not yet implemented")
                }

                override fun onUpcomingItemClick(result: Result) {
                        val bundle = Bundle()
                        bundle.putParcelable("result", result)
                        val intent = Intent(requireContext(), MovieDescriptionActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                })
        }

        tvShowViewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
        tvShowViewModel.movies.observe(viewLifecycleOwner) { shows ->
            tvShow.adapter = TvShowsAdapter(shows)
        }


        //this is fetch data from viewmodel
        viewModel.fetchPopularMovies()
        upComingViewModel.fetchUpcomingMovies()
        tvShowViewModel.fetchTvShows()

    }
    override fun onItemClick(movie: Movie) {
    }

    override fun onUpcomingItemClick(result: Result) {
    }
}

