package com.example.cubosteste.presentation.movies

import android.view.View
import com.example.cubosteste.data.model.movies.MovieResponse
import com.example.cubosteste.presentation.BasePresenter
import com.example.cubosteste.ui.BaseView

interface MovieContract {

    interface View : BaseView<Presenter> {
        var isActive: Boolean
        fun showMovies(movies: List<MovieResponse>)
        fun setLoadingIndicator(show: Boolean)

    }

    interface Presenter : BasePresenter<View> {
        fun getMoviesByQuery(query: String)
        fun getMovies(genreId: Int, page: Int)
        fun destroy()
    }
}