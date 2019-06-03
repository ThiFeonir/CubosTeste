package com.example.cubosteste.data.model.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "original_title") val originalTitle: String,
    @SerializedName(value = "poster_path") val posterPath: String?,
    @SerializedName(value = "overview") val overview: String
)