package com.teste.movies.ui.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.teste.movies.R
import android.support.v7.widget.GridLayoutManager
import com.teste.movies.data.model.Movie
import com.teste.movies.data.service.MoviesRepository
import com.teste.movies.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import android.widget.Toast
import com.teste.movies.BR
import com.teste.movies.ui.main.MainActivity
import com.teste.movies.data.callback.OnGetMoviesCallback
import com.teste.movies.databinding.FragmentMoviesBinding
import com.teste.movies.ui.base.BaseFragment


class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), MoviesNavigator {

    private var viewModel: MoviesViewModel? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_movies
    }

    override fun getViewModel(): MoviesViewModel {
        viewModel = MoviesViewModel()
        return viewModel!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel!!.setNavigator(this)
        viewModel!!.init()

        moviesRecyclerView.layoutManager = GridLayoutManager(this.context, 3)
    }

    override fun success(movies: List<Movie>) {

        progressBarContainer.visibility = View.GONE

        val adapter = MoviesAdapter(movies, false)
        moviesRecyclerView.adapter = adapter
    }

    override fun handlerError(message: String) {
        progressBarContainer.visibility = View.GONE
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

}
