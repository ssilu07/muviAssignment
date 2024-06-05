package com.royals.muviassignment.apiservice

import com.royals.muviassignment.ApiClient
import com.royals.muviassignment.model.MovieResponse
import com.royals.muviassignment.model.NowPlaying
import com.royals.muviassignment.model.TvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = ApiClient.API_KEY): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String = ApiClient.API_KEY): NowPlaying

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String = ApiClient.API_KEY): TvResponse

}