package com.teste.movies.ui.movies

import com.teste.movies.data.callback.OnGetMoviesCallback
import com.teste.movies.data.model.Movie
import com.teste.movies.data.service.MoviesRepository
import com.teste.movies.ui.base.BaseViewModel

class MoviesViewModel : BaseViewModel() {

    private var repository: MoviesRepository? = null

    private lateinit var navigator : MoviesNavigator

    fun init(){
        navigator = getNavigator() as MoviesNavigator

        repository = MoviesRepository.instance

        repository!!.getMovies(object : OnGetMoviesCallback {
            override fun success(movies: List<Movie>) {

                navigator.success(movies)
            }

            override fun handlerError() {
                navigator.handlerError("Please check your internet connection.")
            }
        })
    }
}