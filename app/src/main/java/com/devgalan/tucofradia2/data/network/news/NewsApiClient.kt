package com.devgalan.tucofradia2.data.network.news

import com.devgalan.tucofradia2.data.model.news.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiClient {
    @GET("news")
    suspend fun getNews(): Response<List<News>>
}