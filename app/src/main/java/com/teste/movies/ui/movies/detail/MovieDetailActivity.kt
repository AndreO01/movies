package com.teste.movies.ui.movies.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.teste.movies.R
import com.teste.movies.data.model.Movie
import com.teste.movies.utils.AppPreferences
import com.teste.movies.utils.Constants
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private val prefs = AppPreferences.getInstance(this)

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        title = "Detalhes do Filme"

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        movie = intent.extras.get(Constants.PUT_EXTRA_MOVIE) as? Movie

        val movieDetailPosterImageView: ImageView = findViewById(R.id.movieDetailPosterImageView)
        Glide.with(this).load(Constants.IMAGES_PATH + movie!!.posterPath).into(movieDetailPosterImageView)

        movieDetailTitleText.text = movie!!.title
        movieDetailAverageTextView.text = "Avaliação " + movie!!.rating
        if (!movie!!.releaseDate.isNullOrEmpty() && movie!!.releaseDate!!.length > 4)
            movieDetailYearTextView.text = movie!!.releaseDate!!.substring(0, 4)

        checkIsFavorite()

        movieLikeImage.setOnClickListener {
            if (!prefs.favorites.isNullOrEmpty()) {
                if (!prefs.favorites.contains(movie!!.id.toString()))
                    prefs.favorites = "${prefs.favorites},${movie!!.id}"
            } else {
                prefs.favorites = movie!!.id.toString()
            }

            showLikeImg()
        }

        movieLikeOverImage.setOnClickListener {

            if (!prefs.favorites.isNullOrEmpty()) {

                var idList = prefs.favorites.split(",")

                if (idList.size == 1) {
                    prefs.favorites = ""
                } else {
                    var idStr = ",${movie!!.id}"
                    prefs.favorites = prefs.favorites.replace(idStr, "")
                }
            } else {
                prefs.favorites = ""
            }

            hideLikeImg()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun checkIsFavorite() {

        if (!prefs.favorites.isNullOrEmpty()) {

            var idList = prefs.favorites.split(",")

            idList.forEach {
                if (it.toInt() == movie!!.id) {
                    showLikeImg()
                }
            }
        } else {
            hideLikeImg()
        }
    }

    private fun showLikeImg() {
        movieLikeOverImage.visibility = View.VISIBLE
        movieLikeImage.visibility = View.GONE
    }

    private fun hideLikeImg() {
        movieLikeOverImage.visibility = View.GONE
        movieLikeImage.visibility = View.VISIBLE
    }
}
