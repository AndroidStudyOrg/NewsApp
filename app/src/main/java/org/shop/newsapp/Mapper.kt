package org.shop.newsapp

fun List<NewsItem>.transform(): List<NewsModel> {
    return this.map {
        NewsModel(title = it.title ?: "", link = it.link ?: "", imageUrl = null)
    }
}