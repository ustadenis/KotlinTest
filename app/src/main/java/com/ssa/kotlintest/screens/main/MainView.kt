package com.ssa.kotlintest.screens.main

import com.ssa.kotlintest.models.Article

interface MainView {
    fun onDataUpdated(articles: List<Article>)
    fun onFetchDataError(error: Throwable)
}