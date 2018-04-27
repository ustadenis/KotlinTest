package com.ssa.kotlintest.data.storage.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
        @PrimaryKey(autoGenerate = true) var id: Long,
        @ColumnInfo(name = "source") val source: Source,
        @ColumnInfo(name = "author") val author: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "description") val description: Any,
        @ColumnInfo(name = "url") val url: String,
        @ColumnInfo(name = "urlToImage") val urlToImage: String,
        @ColumnInfo(name = "publishedAt") val publishedAt: String
)

@Entity(tableName = "sources")
data class Source(
        @PrimaryKey(autoGenerate = true) val id: Any,
        @ColumnInfo(name = "name") val name: String
)