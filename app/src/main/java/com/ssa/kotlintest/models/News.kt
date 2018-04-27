package com.ssa.kotlintest.models

import com.google.gson.annotations.SerializedName

data class NewsApiResult(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)

data class Article(
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: Any,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String
)

data class Source(
    @SerializedName("id") val id: Any,
    @SerializedName("name") val name: String
)