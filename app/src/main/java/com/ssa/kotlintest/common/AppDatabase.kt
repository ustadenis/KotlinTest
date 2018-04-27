package com.ssa.kotlintest.common

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ssa.kotlintest.data.storage.daos.ArticleDao
import com.ssa.kotlintest.data.storage.models.Article
import com.ssa.kotlintest.data.storage.models.Source

@Database(entities = arrayOf(Article::class, Source::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDataDao(): ArticleDao
}