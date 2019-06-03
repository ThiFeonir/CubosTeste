package com.example.cubosteste.injection.modules

import com.example.cubosteste.app.utils.Constants.BASE_URL
import com.example.cubosteste.data.WebServiceFactory
import com.example.cubosteste.data.mapper.MovieMapper
import com.example.cubosteste.data.repository.MovieRemoteRepository
import com.example.cubosteste.data.service.MovieWebService
import com.example.cubosteste.domain.repository.movies.MovieRepository
import com.example.cubosteste.domain.usecase.MovieUseCase
import org.koin.dsl.module

val dataModule = module {

    /* Movies */
    single {
        WebServiceFactory.makeWebService<MovieWebService>(
            WebServiceFactory.makeOkHttpClient(WebServiceFactory.provideHttpLoggingInterceptor()),
            BASE_URL,
            WebServiceFactory.makeGson())
    }
    single { MovieRemoteRepository(get(), get()) as MovieRepository }
    single { MovieUseCase(get()) }
    factory { MovieMapper() }

    /*// Resume
    single { ResumeCacheRepository(get()) as ResumeRepository }
    single { ResumeUseCase(get()) }

    *//* Login *//*
    single {
        WebServiceFactory.makeWebService<UserWebService>(
            WebServiceFactory.makeOkHttpClient(WebServiceFactory.provideHttpLoggingInterceptor()),
            BASE_URL,
            WebServiceFactory.makeGson())
    }
    single { LoginUseCase(get()) }
    single { LoginRemoteImpl(get()) as LoginRemoteRepository }*/
}