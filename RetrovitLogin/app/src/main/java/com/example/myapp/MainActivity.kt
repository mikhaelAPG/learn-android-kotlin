package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAction()
    }

    fun initAction() {
        btn_login.setOnClickListener {
            login()
        }
    }

    fun login() {
        val request = UserRequest()
        request.email = et_email.text.toString().trim()
        request.password = et_password.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserAPI::class.java)
        retro.login(request).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                Log.e("token", user!!.data?.token!!)
                Log.e("email", user!!.data?.email!!)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })
    }
}