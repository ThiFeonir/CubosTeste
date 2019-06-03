package com.example.cubosteste.domain.repository.movies

import com.example.cubosteste.data.Result
import com.example.cubosteste.data.model.movies.MovieListResponse
import com.example.cubosteste.data.model.movies.MovieResponse

interface MovieRepository {
    suspend fun getMovies(genreId: Int, page: Int): Result<MovieListResponse>
    suspend fun getMoviesByQuery(query: String): Result<MovieListResponse>
}