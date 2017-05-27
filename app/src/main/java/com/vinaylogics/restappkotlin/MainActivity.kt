package com.vinaylogics.restappkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.vinaylogics.restappkotlin.adapter.PostAdapter
import com.vinaylogics.restappkotlin.model.APIError
import com.vinaylogics.restappkotlin.model.ApiCallBack
import com.vinaylogics.restappkotlin.model.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postRecyclerView.layoutManager = LinearLayoutManager(this)
        postRecyclerView.setHasFixedSize(true)
        Post.getPost(object: ApiCallBack<List<Post>>{
            override fun onSuccess(model: List<Post>?) {
                postRecyclerView.adapter = PostAdapter(model!!)
            }

            override fun onError(model: List<Post>?, error: APIError?) {
            }
        })

    }
}
