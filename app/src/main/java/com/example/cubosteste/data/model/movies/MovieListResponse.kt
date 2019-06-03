package com.example.cubosteste.data.model.movies

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName(value = "results") val result: List<MovieResponse>,
    @SerializedName(value = "page") val page: Int,
    @SerializedName(value = "total_results") val totalResults: Int,
    @SerializedName(value = "total_pages") val totalPages: Int
)