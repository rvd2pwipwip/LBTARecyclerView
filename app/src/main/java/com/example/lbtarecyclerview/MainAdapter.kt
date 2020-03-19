package com.example.lbtarecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf<String>("First title", "Second title", "Third title", "4th")

    // number of items
    override fun getItemCount(): Int {
        return videoTitles.count()
    }
    // set the view holder layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent,false)
        return CustomViewHolder(cellForRow)
    }
    // bind the data for each cell component
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.textView_video_title.text = videoTitles[position]
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}