package com.ssa.kotlintest.screens.main

import com.ssa.kotlintest.common.CoroutineErrorListener
import com.ssa.kotlintest.common.UncaughtCoroutineExceptionHandler
import com.ssa.kotlintest.interactors.NetworkInteractor
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

class MainPresenterImpl(val networkInteractor: NetworkInteractor) : MainPresenter {

    private lateinit var view: MainView

    private var jobs = arrayListOf<Job>()

    override fun bind(view: MainView) {
        this.view = view
        updateData()
    }

    override fun unbind() {
        jobs.forEach({ it.cancel() })
    }

    override fun onRefresh() {
        updateData()
    }

    private fun updateData() {
        jobs.add(launch(UI + UncaughtCoroutineExceptionHandler(CoroutineErrorListener { throwable ->
            view.onFetchDataError(throwable)
        })) {
            val articles = networkInteractor.getNews()
            view.onDataUpdated(articles)
        })
    }
}