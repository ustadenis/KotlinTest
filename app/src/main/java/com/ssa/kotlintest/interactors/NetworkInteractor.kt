package com.ssa.kotlintest.interactors

import com.ssa.kotlintest.models.Article

interface NetworkInteractor {

    suspend fun getNews(): List<Article>

}