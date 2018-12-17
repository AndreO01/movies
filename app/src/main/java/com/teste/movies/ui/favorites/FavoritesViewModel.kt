package com.teste.movies.ui.favorites

import android.content.Context
import com.teste.movies.data.callback.OnGetMoviesCallback
import com.teste.movies.data.model.Movie
import com.teste.movies.data.service.MoviesRepository
import com.teste.movies.ui.base.BaseViewModel
import com.teste.movies.utils.AppPreferences

class FavoritesViewModel : BaseViewModel() {

    private var repository: MoviesRepository? = null

    private lateinit var navigator: FavoritesNavigator

    fun init(context: Context) {

        navigator = getNavigator() as FavoritesNavigator


        repository = MoviesRepository.instance

        repository!!.getMovies(object : OnGetMoviesCallback {
            override fun success(movies: List<Movie>) {

                var favoriteMovies: MutableList<Movie> = arrayListOf()


                val prefs = AppPreferences.getInstance(context)

                if (!prefs.favorites.isNullOrEmpty()) {

                    var idList = prefs.favorites.split(",")

                    movies.forEach {

                        if (prefs.favorites.contains(it.id.toString()))
                            favoriteMovies.add(it)
                    }

                    navigator.success(favoriteMovies)
                }

                else(navigator.handlerError("Você ainda nâo possui favoritos."))
            }

            override fun handlerError() {
                navigator.handlerError("Please check your internet connection.")
            }
        })
    }
}