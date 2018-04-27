package com.ssa.kotlintest.screens.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.ssa.kotlintest.R
import com.ssa.kotlintest.models.Article
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView, SwipeRefreshLayout.OnRefreshListener {

    private val TAG = MainActivity::class.simpleName

    private val mainPresenter: MainPresenter by inject()

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshView.setOnRefreshListener(this)
        initRecyclerView()
        mainPresenter.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.unbind()
    }

    override fun onRefresh() {
        mainPresenter.onRefresh()
        refreshView.isRefreshing = true
    }

    override fun onDataUpdated(articles: List<Article>) {
        adapter.updateData(articles)
        refreshView.isRefreshing = false
    }

    override fun onFetchDataError(error: Throwable) {
        Log.e(TAG, error.message)
        refreshView.isRefreshing = false
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = NewsAdapter(listOf(), this)
        recyclerView.adapter = adapter
    }
}
