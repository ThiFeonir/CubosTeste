package com.example.cubosteste.injection.modules

import com.example.cubosteste.presentation.movies.DramaMovieContract
import com.example.cubosteste.presentation.movies.DramaMoviePresenter
import com.example.cubosteste.presentation.movies.MovieContract
import com.example.cubosteste.presentation.movies.MoviePresenter
import com.example.cubosteste.ui.movies.view.FragmentActionMovie
import com.example.cubosteste.ui.movies.view.FragmentDramaMovie
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val presentationModule = module {
    factory { FragmentActionMovie() }
    factory { MoviePresenter(get()) as MovieContract.Presenter }

    factory { FragmentDramaMovie() }
    factory { DramaMoviePresenter(get()) as DramaMovieContract.Presenter }
}