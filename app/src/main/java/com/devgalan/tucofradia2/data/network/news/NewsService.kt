package com.devgalan.tucofradia2.data.network.news

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.news.News
import com.devgalan.tucofradia2.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(private val api: NewsApiClient) : ApiService() {

    suspend fun getNews(resultActions: ResultActions<List<News>>): List<News> {
        return withContext(Dispatchers.IO) {
            val response =
                api.getNews()

            doResultActions(response, resultActions, emptyList())
        }
    }
}