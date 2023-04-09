package com.example.myfirstapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.model.realm.User

class UserAdapter(val context: Context): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val users: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindModel(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUser(data: List<User>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(i: View): RecyclerView.ViewHolder(i) {

        val tvid: TextView = i.findViewById(R.id.tv_id)
        val tvnama: TextView = i.findViewById(R.id.tv_nama)
        val tvemail: TextView = i.findViewById(R.id.tv_email)


        fun bindModel(u: User) {
            tvid.text = u.getId().toString()
            tvnama.text = u.getNama()
            tvemail.text = u.getEmail()
        }
    }
}