package com.vinaylogics.restappkotlin.api

import com.vinaylogics.restappkotlin.model.Post

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by vinaylogics on 25-05-2017.
 */

interface PostApi {
    @get:GET("posts")
    val posts: Call<List<Post>>

    @GET("posts/{id}")
    fun post(@Path("id") id : Int ) : Call<Post>
}
