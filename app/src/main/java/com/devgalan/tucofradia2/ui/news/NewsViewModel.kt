package com.devgalan.tucofradia2.ui.news

import androidx.lifecycle.ViewModel
import com.devgalan.tucofradia2.data.model.news.NewsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsProvider: NewsProvider
) : ViewModel() {

    fun getNews() = newsProvider.news
}