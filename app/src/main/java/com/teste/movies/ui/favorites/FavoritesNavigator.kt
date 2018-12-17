package com.teste.movies.ui.favorites

import com.teste.movies.data.model.Movie
import com.teste.movies.ui.base.BaseNavigator

interface FavoritesNavigator : BaseNavigator {

    fun success(movies: List<Movie>)
    fun handlerError(message:String)
}