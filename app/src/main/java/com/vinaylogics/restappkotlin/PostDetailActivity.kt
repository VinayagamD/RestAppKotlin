package com.vinaylogics.restappkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vinaylogics.restappkotlin.model.APIError
import com.vinaylogics.restappkotlin.model.ApiCallBack
import com.vinaylogics.restappkotlin.model.Post
import timber.log.Timber

import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    companion object Factory{
        var id:Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        Post.getPost(PostDetailActivity.id, object: ApiCallBack<Post>{
            override fun onSuccess(model: Post?) {
                idTextView.text = model?.id.toString()
                userIdTextView.text = model?.userId.toString()
                titleTextView.text = model?.title
                bodyTextView.text = model?.body
            }

            override fun onError(model: Post?, error: APIError?) = Timber.d(error?.message)
        })
    }
}
