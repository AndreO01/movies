package com.teste.movies.ui.movies

import com.teste.movies.data.model.Movie
import com.teste.movies.ui.base.BaseNavigator

interface MoviesNavigator : BaseNavigator{

    fun success(movies: List<Movie>)
    fun handlerError(message:String)
}