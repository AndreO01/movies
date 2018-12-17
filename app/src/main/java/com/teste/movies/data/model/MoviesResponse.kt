package com.teste.movies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MoviesResponse : Serializable {
    @SerializedName("page")
    @Expose
    val page: Int = 0

    @SerializedName("total_results")
    @Expose
    val totalResults: Int = 0

    @SerializedName("results")
    @Expose
    val movies: List<Movie>? = null

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int = 0


}