package com.example.lbtarecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*


// add homeFeed parameter to MainAdapter class to get access to JSON data
class MainAdapter(private val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

//    val videoTitles = listOf<String>("First title", "Second title", "Third title", "4th")

    // number of items
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }
    // set the view holder layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent,false)
        return CustomViewHolder(cellForRow)
    }
    // bind the data for each cell component
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)
        val thumbnailImage = video.imageUrl
        val profileImage = video.channel.profileImageUrl
        val thumbnailImageView = holder.view.imageView_video_thumbnail
        val profileImageView = holder.view.imageView_channel_profile

        Picasso.get().load(thumbnailImage).into(thumbnailImageView)
        Picasso.get().load(profileImage).into(profileImageView)
        holder.view.textView_video_title.text = video.name
        holder.view.textView_channel_name.text = video.channel.name + "  •  " + video.numberOfViews + " views \n4 days ago"
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}