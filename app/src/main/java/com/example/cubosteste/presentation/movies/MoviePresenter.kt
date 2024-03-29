package com.example.cubosteste.presentation.movies

import android.provider.Contacts
import android.util.Log
import android.view.View
import com.example.cubosteste.data.Result
import com.example.cubosteste.data.model.movies.MovieResponse
import com.example.cubosteste.domain.usecase.MovieUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

class MoviePresenter (private val movieUseCase: MovieUseCase,
                      private val uiContext: CoroutineContext = Dispatchers.Main)
    : CoroutineScope, MovieContract.Presenter, KoinComponent {

    override lateinit var view: MovieContract.View

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
                    view.showMovieNotFound()
                }
            }
        }

    }

    override fun getMoviesByQuery(query: String) = launchSilent {

        val result = movieUseCase.getMoviesByQuery(query)

        withContext(uiContext) {
            if (result is Result.Success) {
                Log.d("Sucess", "tem data")
                val moviesToShow = ArrayList<MovieResponse>()

                for (movies in result.data.result) {
                    moviesToShow.add(movies)
                }

                view.setLoadingIndicator(false)
                view.showMovies(moviesToShow)

            } else {
                view.showMovieNotFound()
            }
        }
    }

    fun launchSilent(
        context: CoroutineContext = Dispatchers.Default,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        GlobalScope.launch(context, start, block)
    }

    override fun destroy() {
        job.cancel()
    }

}