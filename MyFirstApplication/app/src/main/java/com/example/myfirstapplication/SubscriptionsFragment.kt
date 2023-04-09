package com.example.myfirstapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapplication.adapter.BlogAdapter
import com.example.myfirstapplication.model.Placeholder
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_subscriptions.*
import okhttp3.*
import java.io.IOException

class SubscriptionsFragment : Fragment() {

    lateinit var blogAdapter: BlogAdapter
    var lm = LinearLayoutManager(activity)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscriptions, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
    }

    fun initView() {
        rv_blog.layoutManager = lm
        blogAdapter = BlogAdapter(activity!!)
        rv_blog.adapter = blogAdapter
    }

    fun getData() {
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/posts")
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Failed", e.message!!)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                var gson = GsonBuilder().create()
                var result = gson.fromJson(body, Array<Placeholder>::class.java).toList()

                activity?.runOnUiThread {
                    tv_subscription.text = result?.get(0)?.body.toString()
                    blogAdapter.setBlog(result!!)
                }

            }

        })
    }
}