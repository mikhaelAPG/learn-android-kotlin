package com.example.myfirstapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.MovieDetailActivity
import com.example.myfirstapplication.R
import com.example.myfirstapplication.listener.OnItemClickListener
import com.example.myfirstapplication.model.Movie

class MovieAdapter(val context: Context): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()
    private var onSelectedListener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bindModel(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovie(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    fun getMovie(): MutableList<Movie> {
        return movies
    }

    inner class MovieViewHolder(item: View): RecyclerView.ViewHolder(item) {

        val txtTitle: TextView = item.findViewById(R.id.tv_judul)
        val txtDescription: TextView = item.findViewById(R.id.tv_deskripsi)
        val cardViewMovie: CardView = item.findViewById(R.id.cv_movie)

        fun bindModel(m: Movie) {
            txtTitle.text = m.getTitle()
            txtDescription.text = m.getDescription()

//            cardViewMovie.setOnClickListener {
//                var i = Intent(context, MovieDetailActivity::class.java)
//                i.putExtra("title", m.getTitle())
//                i.putExtra("description", m.getDescription())
//                context.startActivity(i)
//            }
        }

        init {
            cardViewMovie.setOnClickListener { onSelectedListener?.onItemClick(it, layoutPosition) }
        }
    }

    fun setOnClickItemListener(onClickItemListener: OnItemClickListener) {
        this.onSelectedListener = onClickItemListener
    }
}