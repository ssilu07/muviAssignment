package com.royals.muviassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.royals.muviassignment.model.Result
import com.royals.muviassignment.model.Show
import com.royals.muviassignment.repository.MovieRepository
import kotlinx.coroutines.launch

class TvShowsViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableLiveData<List<Show>>()
    val movies: LiveData<List<Show>> get() = _movies

    fun fetchTvShows() {
        viewModelScope.launch {
            try {
                val movies = repository.getPopularTvShows()
                _movies.postValue(movies)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}