package com.teste.movies.data.tmdb.api

import com.teste.movies.data.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDbApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Call<MoviesResponse>
}