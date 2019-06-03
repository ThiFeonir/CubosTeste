package com.example.cubosteste.presentation.movies

import com.example.cubosteste.data.model.movies.MovieResponse
import com.example.cubosteste.presentation.BasePresenter
import com.example.cubosteste.ui.BaseView

interface DramaMovieContract {

    interface View : BaseView<Presenter> {
        var isActive: Boolean
        fun showMovies(movies: List<MovieResponse>)
        fun setLoadingIndicator(show: Boolean)

    }

    interface Presenter : BasePresenter<View> {
        fun getMovies(genreId: Int, page: Int)
        fun destroy()
    }
}