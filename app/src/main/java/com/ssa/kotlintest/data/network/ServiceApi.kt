package com.ssa.kotlintest.data.network

import com.ssa.kotlintest.BuildConfig
import com.ssa.kotlintest.models.NewsApiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("v2/top-headlines/")
    fun getNews(@Query("category") category: String = "business",
                @Query("country") country: String = "us",
                @Query("apiKey") apiKey: String = BuildConfig.API_KEY): Call<NewsApiResult>
}