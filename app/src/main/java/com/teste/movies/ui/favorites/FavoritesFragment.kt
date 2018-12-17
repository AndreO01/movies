package com.teste.movies.ui.favorites

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.teste.movies.BR

import com.teste.movies.R
import com.teste.movies.data.model.Movie
import com.teste.movies.databinding.FragmentFavoriteBinding
import com.teste.movies.ui.base.BaseFragment
import com.teste.movies.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoritesFragment : BaseFragment<FragmentFavoriteBinding, FavoritesViewModel>(), FavoritesNavigator {

    private var viewModel: FavoritesViewModel? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_favorite
    }

    override fun getViewModel(): FavoritesViewModel {
        viewModel = FavoritesViewModel()
        return viewModel!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel!!.setNavigator(this)
        viewModel!!.init(this.context!!)

        favoritesRecyclerView.layoutManager = GridLayoutManager(this.context, 3)
    }

    override fun success(movies: List<Movie>) {
        progressBarContainer.visibility = View.GONE


        val adapter = MoviesAdapter(movies, false)
        favoritesRecyclerView.adapter = adapter
    }

    override fun handlerError(message: String) {
        progressBarContainer.visibility = View.GONE
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

}
