package com.example.snaphelper

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test = 121

        val result1 = test.let {
            10086
        }

        val result2 = test.also {
            10086
        }

        Log.i("MainActivity", "$result1")
        Log.i("MainActivity", "$result2")

        for (i in 1..5) {
            Log.i("MainActivity", "$i")
        }

        val datas = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
        val mAdapter = MyAdapter()
        mAdapter.datas = datas
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
//            LinearSnapHelper().attachToRecyclerView(this)
//            PagerSnapHelper().attachToRecyclerView(this)
            GallerySnapHelper().attachToRecyclerView(this)
            adapter = mAdapter
        }
    }
}

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var datas: MutableList<Int>? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null

        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView?.apply {
            text = datas?.get(position).toString()
        }
    }
}
