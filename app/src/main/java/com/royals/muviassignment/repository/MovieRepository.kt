package com.royals.muviassignment.repository

import com.royals.muviassignment.ApiClient
import com.royals.muviassignment.model.Movie
import com.royals.muviassignment.model.Result
import com.royals.muviassignment.model.Show
import com.royals.muviassignment.model.TvResponse

class MovieRepository {
    private val apiService = ApiClient.api

    suspend fun getPopularMovies(): List<Movie> {
        return apiService.getPopularMovies().results
    }

    suspend fun getUpcomingMovies(): List<Result> {
        return apiService.getUpcomingMovies().results
    }

    suspend fun getPopularTvShows(): List<Show> {
        return apiService.getPopularTvShows().results
    }
}