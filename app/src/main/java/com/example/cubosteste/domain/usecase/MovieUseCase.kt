package com.example.cubosteste.domain.usecase

import com.example.cubosteste.data.Result
import com.example.cubosteste.data.model.movies.MovieListResponse
import com.example.cubosteste.data.repository.MovieRemoteRepository
import com.example.cubosteste.domain.repository.movies.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMovies(genreId: Int, page: Int) : Result<MovieListResponse> {

        val movieResult = movieRepository.getMovies(genreId, page)

        return when (movieResult) {
            is Result.Success -> movieResult
            is Result.Error -> Result.Error(movieResult.exception)
        }
    }

    suspend fun getMoviesByQuery(query: String) : Result<MovieListResponse> {
        val queryResult = movieRepository.getMoviesByQuery(query)

        return when (queryResult) {
            is Result.Success -> queryResult
            is Result.Error -> Result.Error(queryResult.exception)
        }
    }
}