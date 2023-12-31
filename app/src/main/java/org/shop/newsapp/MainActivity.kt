package org.shop.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.jsoup.Jsoup
import org.shop.newsapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://news.google.com")
        .addConverterFactory(
            TikXmlConverterFactory.create(
                TikXml.Builder().exceptionOnUnreadXml(false).build()
            )
        )
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        newsAdapter = NewsAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
            startActivity(intent)
        }

        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }

        val newsService = retrofit.create(NewsService::class.java)

        newsService.mainFeed().enqueue(object : Callback<NewsRss> {
            override fun onResponse(call: Call<NewsRss>, response: Response<NewsRss>) {
                Log.e("MainActivity", "${response.body()?.channel?.items}")

                val transformedList = response.body()?.channel?.items.orEmpty().transform()

                newsAdapter.submitList(transformedList)

                transformedList.forEachIndexed { index, news ->
                    Thread {
                        try {
                            Log.e("MainActivity", "link: ${news.link}")
                            val jsoup = Jsoup.connect(news.link).ignoreContentType(true).get()
                            val elements = jsoup.select("meta[property^=og:]")

                            Log.e("MainActivity", "elements with property og: $elements")

                            val ogImageNode = elements.find { node ->
                                node.attr("property") == "og:image"
                            }

                            Log.e("MainActivity", "image: $ogImageNode")
                            news.imageUrl = ogImageNode?.attr("content")
                            Log.e(
                                "MainActivity",
                                "imageUrl: ${news.imageUrl}\n node: ${ogImageNode?.attr("content")}"
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        runOnUiThread {
                            newsAdapter.notifyItemChanged(index)
                        }
                    }.start()
                }
            }

            override fun onFailure(call: Call<NewsRss>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}