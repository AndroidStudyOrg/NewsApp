package org.shop.newsapp

import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("/rss?hl=ho&gl=KR&ceid=KR:ko")
    fun mainFeed(): Call<NewsRss>
}