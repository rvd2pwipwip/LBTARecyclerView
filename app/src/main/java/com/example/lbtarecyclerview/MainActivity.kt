package com.example.lbtarecyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity: AppCompatActivity() {

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


                runOnUiThread { // must take place in the main thread (same thread as recyclerView's view hierarchy)
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}
