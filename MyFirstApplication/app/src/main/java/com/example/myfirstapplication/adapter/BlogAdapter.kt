package com.example.myfirstapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.model.Placeholder

class BlogAdapter(val context: Context): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    private val blogs: MutableList<Placeholder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(LayoutInflater.from(context).inflate(R.layout.item_blog, parent, false))
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bindModel(blogs[position])
    }

    fun setBlog(data: List<Placeholder>) {
        blogs.clear()
        blogs.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return blogs.size
    }

    inner class BlogViewHolder(item: View): RecyclerView.ViewHolder(item) {

        val txtTitle: TextView = item.findViewById(R.id.tv_title)
        val txtDesc: TextView = item.findViewById(R.id.tv_desc)

        fun bindModel(b: Placeholder) {
            txtTitle.text = b.title
            txtDesc.text = b.body
        }
    }
}