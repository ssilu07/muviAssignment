package com.royals.muviassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.royals.muviassignment.model.Movie
import com.royals.muviassignment.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getPopularMovies()
                _movies.postValue(movies)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

}