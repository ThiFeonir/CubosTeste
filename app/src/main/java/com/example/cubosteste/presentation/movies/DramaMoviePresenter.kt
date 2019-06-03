package com.example.cubosteste.presentation.movies

import com.example.cubosteste.data.Result
import com.example.cubosteste.data.model.movies.MovieResponse
import com.example.cubosteste.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

class DramaMoviePresenter (private val movieUseCase: MovieUseCase,
                      private val uiContext: CoroutineContext = Dispatchers.Main)
    : CoroutineScope, DramaMovieContract.Presenter, KoinComponent {

    override lateinit var view: DramaMovieContract.View

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    override fun start() {
        //
    }

    override fun getMovies(genreId: Int, page: Int) {

        view.setLoadingIndicator(true)

        job = launch {
            val result = movieUseCase.getMovies(genreId, page)

            if (result is Result.Success) {
                val moviesToShow = ArrayList<MovieResponse>()

                for (movies in result.data.result) {
                    moviesToShow.add(movies)
                }

                if (view.isActive) {
                    view.setLoadingIndicator(false)
                    view.showMovies(moviesToShow)
                }

            } else {
                if (view.isActive) {
                    //moviesView.showLoadingReposError()
                }
            }
        }

    }

    override fun destroy() {
        job.cancel()
    }

}