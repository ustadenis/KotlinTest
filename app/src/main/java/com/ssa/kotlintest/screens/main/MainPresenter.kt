package com.ssa.kotlintest.screens.main

interface MainPresenter {

    fun bind(view: MainView)
    fun unbind()

    fun onRefresh()
}