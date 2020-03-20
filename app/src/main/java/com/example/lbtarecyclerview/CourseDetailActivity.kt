package com.example.lbtarecyclerview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class CourseDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main) //use main activity layout
//        recyclerView_main.setBackgroundColor(Color.LTGRAY) //use main activity layout's recycler view

        recyclerView_main.layoutManager = LinearLayoutManager(this) //context to know how (where) to render itself
        recyclerView_main.adapter = CourseDetailAdapter()
    }


    private class CourseDetailAdapter: RecyclerView.Adapter<CourseLessonViewHolder>() {
        override fun getItemCount(): Int {
            return 5
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val lessonView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

//            val greyView = View(parent.context)
//            greyView.setBackgroundColor(Color.LTGRAY)
//            greyView.minimumHeight = 50
            return CourseLessonViewHolder(lessonView)
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {

        }
    }

    private class  CourseLessonViewHolder(val lessonView: View): RecyclerView.ViewHolder(lessonView) {

    }

}
