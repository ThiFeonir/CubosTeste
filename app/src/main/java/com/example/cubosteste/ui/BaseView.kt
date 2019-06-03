package com.example.cubosteste.ui

import com.example.cubosteste.presentation.BasePresenter

interface BaseView<out T : BasePresenter<*>> {

    val presenter: T

}
