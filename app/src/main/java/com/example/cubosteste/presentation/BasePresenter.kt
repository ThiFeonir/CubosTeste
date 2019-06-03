package com.example.cubosteste.presentation

interface BasePresenter<T> {

    fun start()

    var view: T
}