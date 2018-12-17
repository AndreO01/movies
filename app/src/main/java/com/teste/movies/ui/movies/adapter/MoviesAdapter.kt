package com.teste.movies.ui.movies.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.teste.movies.R
import com.teste.movies.data.model.Movie
import com.teste.movies.ui.movies.detail.MovieDetailActivity
import com.teste.movies.utils.Constants

class MoviesAdapter(private val movies: List<Movie>, private val isFavorite: Boolean) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moviePosterImage.setOnClickListener{

            val intent = Intent(context!! as Activity, MovieDetailActivity::class.java)
            intent.putExtra(Constants.PUT_EXTRA_MOVIE, movies[position])
            intent.putExtra(Constants.PUT_EXTRA_FAVORITE, isFavorite)
            context!!.startActivity(intent)
        }
        Glide.with(context!!).load(Constants.IMAGES_PATH + movies[position].posterPath).into(holder.moviePosterImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePosterImage: ImageView = itemView.findViewById(R.id.moviePosterImage)
    }
}