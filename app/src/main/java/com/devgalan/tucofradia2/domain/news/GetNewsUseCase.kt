package com.devgalan.tucofradia2.domain.news

import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.news.News
import com.devgalan.tucofradia2.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(resultActions: ResultActions<List<News>>) =
        repository.getNews(resultActions)
}