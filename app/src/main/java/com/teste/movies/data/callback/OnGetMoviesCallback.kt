package com.teste.movies.data.callback

import com.teste.movies.data.model.Movie

interface OnGetMoviesCallback {

    fun success(movies: List<Movie>)

    fun handlerError()
}