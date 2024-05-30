package com.devgalan.tucofradia2.data.repository

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.news.News
import com.devgalan.tucofradia2.data.model.news.NewsProvider
import com.devgalan.tucofradia2.data.network.news.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsService,
    private val newsProvider: NewsProvider
) {
    suspend fun getNews(resultActions: ResultActions<List<News>>) {
        val response = api.getNews(resultActions)
        newsProvider.news = (response)
    }
}