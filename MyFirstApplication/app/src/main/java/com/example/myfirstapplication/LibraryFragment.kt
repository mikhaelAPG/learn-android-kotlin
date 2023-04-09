package com.example.myfirstapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfirstapplication.model.Comment
import com.example.myfirstapplication.model.user.UserRequest
import com.example.myfirstapplication.model.user.UserResponse
import com.example.myfirstapplication.service.CommentAPI
import com.example.myfirstapplication.service.UserAPI
import com.example.myfirstapplication.util.Retro
import kotlinx.android.synthetic.main.fragment_library.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LibraryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCommentAPI()
        initAction()
    }

    fun initAction() {
        btn_created.setOnClickListener {
            postUserAPI()
        }

        btn_updated.setOnClickListener {
            editUserAPI()
        }

        btn_deleted.setOnClickListener {
            deleteUserAPI()
        }
    }

    fun getCommentAPI() {
        val retro = Retro().getRetroClientInstance("https://jsonplaceholder.typicode.com/").create(CommentAPI::class.java)
        retro.getComment().enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val comment = response.body()
                for (c in comment!!) {
                    Log.e("Hasil", c.email!!)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
               Log.e("Failed", t.message.toString())
            }

        })
    }

    fun postUserAPI() {
        val userReq = UserRequest()
        userReq.name = et_name.text.toString()
        userReq.job = et_job.text.toString()


        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(UserAPI::class.java)
        retro.createUser(userReq).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                tv_result_name.text = "Name : " +  user!!.name.toString()
                tv_result_job.text = "Job : " + user!!.job.toString()
                tv_result_id.text = "Id : " + user!!.id.toString()
                tv_result_created_at.text = "Created At : " + user!!.createdAt.toString()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })
    }

    fun editUserAPI() {
        val userReq = UserRequest()
        userReq.name = et_name.text.toString()
        userReq.job = et_job.text.toString()


        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(UserAPI::class.java)
        retro.ediUser(et_id.text.toString().toInt(), userReq).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                tv_result_name.text = "Name : " +  user!!.name.toString()
                tv_result_job.text = "Job : " + user!!.job.toString()
                tv_result_created_at.text = "Updated At : " + user!!.updatedAt.toString()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })
    }

    fun deleteUserAPI() {
        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(UserAPI::class.java)
        retro.deleteUser(et_id.text.toString().toInt()).enqueue(object : Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val res = response.code()

                Toast.makeText(context, "Data Deleted with response code " + res, Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })
    }
}