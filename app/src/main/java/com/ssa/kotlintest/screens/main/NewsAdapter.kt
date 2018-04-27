package com.ssa.kotlintest.screens.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ssa.kotlintest.R
import com.ssa.kotlintest.models.Article
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var news: List<Article>, val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    fun updateData(news: List<Article>) {
        this.news = news
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            itemView.title.text = article.title
            itemView.author.text = article.author
            Picasso.with(context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.placeholder)
                    .into(itemView.icon)
        }
    }
}