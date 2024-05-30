package com.devgalan.tucofradia2.data.model.news

data class News(
    val id: Long,
    val title: String,
    val body: String,
    val imagePath: String?,
    val date: String
)