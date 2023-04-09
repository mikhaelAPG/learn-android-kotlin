package com.example.myfirstapplication.service

import com.example.myfirstapplication.model.Comment
import retrofit2.Call
import retrofit2.http.GET

interface CommentAPI {
    @GET("comments")
    fun getComment() : Call<List<Comment>>
}