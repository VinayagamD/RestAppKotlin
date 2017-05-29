package com.vinaylogics.restappkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vinaylogics.restappkotlin.adapter.PostAdapter
import com.vinaylogics.restappkotlin.listeners.OnRecyclerItemClickListeners
import com.vinaylogics.restappkotlin.model.APIError
import com.vinaylogics.restappkotlin.model.ApiCallBack
import com.vinaylogics.restappkotlin.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), OnRecyclerItemClickListeners<PostAdapter.PostViewHolder, Post> {
    override fun onRecyclerItemClick(h: PostAdapter.PostViewHolder, v: View, d: Post, pos: Int) {
        PostDetailActivity.id = d.id as Int
        startActivity(Intent(this@MainActivity, PostDetailActivity::class.java))
    }

    var postAdapter: PostAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postRecyclerView.layoutManager = LinearLayoutManager(this)
        postRecyclerView.setHasFixedSize(true)
        Post.getPosts(object: ApiCallBack<List<Post>>{
            override fun onSuccess(model: List<Post>?) {
                postAdapter =  PostAdapter(model!!)
                postRecyclerView.adapter = postAdapter
                postAdapter!!.onRecyclerViewItemClickListener = this@MainActivity
            }

            override fun onError(model: List<Post>?, error: APIError?) = Timber.d(error?.message)
        })

    }
}
