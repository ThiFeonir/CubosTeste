package com.example.cubosteste.data.repository

import com.example.cubosteste.app.utils.Constants
import com.example.cubosteste.data.RemoteDataNotFoundException
import com.example.cubosteste.data.Result
import com.example.cubosteste.data.mapper.MovieMapper
import com.example.cubosteste.data.model.movies.MovieListResponse
import com.example.cubosteste.data.service.MovieWebService
import com.example.cubosteste.domain.repository.movies.MovieRepository

class MovieRemoteRepository(private val movieWebService: MovieWebService,
                            private val movieMapper : MovieMapper
) : MovieRepository {


    override suspend fun getMoviesByQuery(
        query: String)
            : Result<MovieListResponse> {

        val result = movieWebService.searchMovie(
            Constants.API_KEY,
            query = query).await()

        if (result.result.isNotEmpty()) return Result.Success(result)

        return Result.Error(RemoteDataNotFoundException())
    }

    override suspend fun getMovies(genreId: Int,
                                   page: Int)
            : Result<MovieListResponse> {

        val result = movieWebService.fetchMovies(
            Constants.API_KEY,
            page,
            listOf(genreId)).await()

        if (result.result.isNotEmpty()) return Result.Success(result)

        return Result.Error(RemoteDataNotFoundException())
    }

}