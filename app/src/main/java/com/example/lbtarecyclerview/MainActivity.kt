package com.example.lbtarecyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView_main.setBackgroundColor(Color.LTGRAY)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = MainAdapter()

        fetchJSON()
    }

    fun fetchJSON() {
        println("Attempting to fetch JSON")

        val url = "https://api.letsbuildthatapp.com/youtube/home_feed" //api data endpoint

        val request = Request.Builder().url(url).build() // request for specified url

        val client = OkHttpClient() // client can make a url request

        client.newCall(request).enqueue(object: Callback { // enqueue means the call is done in the background
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)


                runOnUiThread { // must take place in the main thread (same thread as recyclerView's view hierarchy
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}

//id: 1,
//name: "Instagram Firebase - Learn How to Create Users, Follow, and Send Push Notifications",
//link: "https://www.letsbuildthatapp.com/course/instagram-firebase",
//imageUrl: "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/04782e30-d72a-4917-9d7a-c862226e0a93",
//numberOfViews: 20000,
//channel: {
//    name: "Lets Build That App",
//    profileImageUrl: "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/dda5bc77-327f-4944-8f51-ba4f3651ffdf",
//    numberOfSubscribers: 100000
//}

class HomeFeed(val videos: List<Videos>)

class Videos(val id: Int, val name: String, val link: String, val imageUrl: String, val numberOfViews: Int, val channel: Channel) // val makes properties available publicly

class Channel(val name: String, val profileImageUrl: String, val numberOfSubscribers: Int)
