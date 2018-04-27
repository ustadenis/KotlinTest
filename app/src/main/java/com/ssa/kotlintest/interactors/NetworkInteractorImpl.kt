package com.ssa.kotlintest.interactors

import com.ssa.kotlintest.data.network.ServiceApi
import com.ssa.kotlintest.models.Article
import ru.gildor.coroutines.retrofit.await

class NetworkInteractorImpl(val serviceApi: ServiceApi): NetworkInteractor {
    suspend override fun getNews(): List<Article> {
        return serviceApi.getNews().await().articles
    }
}