package com.royals.muviassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.royals.muviassignment.model.Result
import com.royals.muviassignment.repository.MovieRepository
import kotlinx.coroutines.launch

class UpComingViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> get() = _movies

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getUpcomingMovies()
                _movies.postValue(movies)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}