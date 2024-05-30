package com.devgalan.tucofradia2.data.model.news

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsProvider @Inject constructor() {
//    var news:List<News> = emptyList()
    var news:List<News> = listOf(
        News(1, "Noticia 1", "Cuerpo de la noticia 1", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-01"),
        News(2, "Noticia 2", "Cuerpo de la noticia 2", "", "2021-09-02"),
        News(3, "Noticia 3", "Cuerpo de la noticia 3", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-03"),
        News(4, "Noticia 4", "Cuerpo de la noticia 4", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-04"),
        News(5, "Noticia 5", "Cuerpo de la noticia 5", "", "2021-09-05"),
        News(6, "Noticia 6", "Cuerpo de la noticia 6", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-06"),
        News(7, "Noticia 7", "Cuerpo de la noticia 7", "", "2021-09-07"),
        News(8, "Noticia 8", "Cuerpo de la noticia 8", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-08"),
        News(9, "Noticia 9", "Cuerpo de la noticia 9", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-09"),
        News(10, "Noticia 10", "Cuerpo de la noticia 10", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-10"),
        News(11, "Noticia 11", "Cuerpo de la noticia 11", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-11"),
        News(12, "Noticia 12", "Cuerpo de la noticia 12", "https://i.blogs.es/0ca9a6/aa/1366_2000.webp", "2021-09-12"),
    )
}