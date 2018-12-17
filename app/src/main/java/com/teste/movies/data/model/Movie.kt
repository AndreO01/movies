package com.teste.movies.data.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Movie : Serializable {
    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null

    @SerializedName("vote_average")
    @Expose
    val rating: Float = 0.toFloat()

    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>? = null

//    @SerializedName("overview")
//    @Expose
//    val overview: String? = null

}