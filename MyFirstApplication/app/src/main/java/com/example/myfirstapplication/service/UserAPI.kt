package com.example.myfirstapplication.service

import com.example.myfirstapplication.model.user.UserRequest
import com.example.myfirstapplication.model.user.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {
    @POST("api/users")
    fun createUser(@Body req: UserRequest) : Call<UserResponse>

    @PUT("api/users/{id}")
    fun ediUser(@Path("id") id: Int, @Body req: UserRequest) : Call<UserResponse>

    @DELETE("api/users/{id}")
    fun deleteUser(@Path("id")  id: Int) : Call<Int>
}