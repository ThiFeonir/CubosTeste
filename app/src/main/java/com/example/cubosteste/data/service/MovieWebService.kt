package com.example.cubosteste.data.service

import com.example.cubosteste.data.model.movies.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieWebService {

    @GET("discover/movie")
    fun fetchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("with_genres") withGenresId: List<Int>,
        @Query("language") language: String = "pt-BR"
    ): Deferred<MovieListResponse>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("query") query: String,
        @Query("language") language: String = "pt-BR"
    ): Deferred<MovieListResponse>
}