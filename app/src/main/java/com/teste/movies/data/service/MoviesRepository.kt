package com.teste.movies.data.service

import com.teste.movies.data.model.MoviesResponse
import com.teste.movies.data.callback.OnGetMoviesCallback
import com.teste.movies.data.tmdb.api.TMDbApi
import com.teste.movies.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class MoviesRepository private constructor(private val api: TMDbApi) {

    var page : Int = 2

    fun getMovies(callback: OnGetMoviesCallback) {
        api.getPopularMovies(Constants.API_KEY)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val moviesResponse = response.body()
                        if (moviesResponse?.movies != null) {
                            callback.success(moviesResponse.movies)
                        } else {
                            callback.handlerError()
                        }
                    } else {
                        callback.handlerError()
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    callback.handlerError()
                }
            })
    }

    companion object {

        private var repository: MoviesRepository? = null

        val instance: MoviesRepository
            get() {
                if (repository == null) {

                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    repository = MoviesRepository(retrofit.create(TMDbApi::class.java))
                }

                return repository!!
            }
    }
}