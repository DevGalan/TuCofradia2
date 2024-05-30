package com.devgalan.tucofradia2.data.model.news

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsProvider @Inject constructor() {
    var news: List<News> = emptyList()
}