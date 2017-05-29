package com.vinaylogics.restappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vinaylogics.restappkotlin.api.PostApi
import com.vinaylogics.restappkotlin.api.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by vinaylogics on 25-05-2017.
 */
data class Post(
@SerializedName("userId")
@Expose
var userId : Int?,
@SerializedName("id")
@Expose
var id : Int?,
@SerializedName("title")
@Expose
var title:String?,
@SerializedName("body")
@Expose
var body: String?
) {

companion object Factory{
    fun getPosts( callBack: ApiCallBack<List<Post>>) {
        val api = ServiceGenerator.createService(PostApi::class.java)
        val call = api.posts
        queueListCall(call,callBack)

    }

    fun getPost(postId: Int,  callBack: ApiCallBack<Post>){
        val api = ServiceGenerator.createService(PostApi::class.java)
        val call = api.post(postId)
        queueCall(call,callBack)
    }


    private fun queueCall( call: Call<Post> , callBack: ApiCallBack<Post>){
        call.enqueue(object: Callback<Post>{
            override fun onFailure(call: Call<Post>?, t: Throwable?) = callBack.onError( null , APIError(APIError.CONNECTION_ERROR, t?.message))


            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                if (response?.isSuccessful as Boolean){
                    callBack.onSuccess(response.body() as Post)
                }else{
                    val x : Int = response.code()
                    when (x){
                        401 -> callBack.onError(response.body() as  Post , APIError(APIError.NOT_AUTHORIZED, "Un authorized") )
                        else -> callBack.onError(response.body() as Post , APIError(APIError.RECORD_NOT_FOUND_ERROR, "Not Found") )
                    }
                }
            }
        })
    }
    private fun queueListCall( call: Call<List<Post>> ,  callBack: ApiCallBack<List<Post>>){
        call.enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                if (response?.isSuccessful as Boolean){
                    callBack.onSuccess(response.body() as List<Post>)
                }else{
                    val x : Int = response.code()
                   when (x){
                       401 -> callBack.onError(response.body() as  List<Post> , APIError(APIError.NOT_AUTHORIZED, "Un authorized") )
                       else -> callBack.onError(response.body() as  List<Post> , APIError(APIError.RECORD_NOT_FOUND_ERROR, "Not Found") )
                   }
                }
            }

             override fun onFailure(call: Call<List<Post>>?, t: Throwable?) = callBack.onError( null , APIError(APIError.CONNECTION_ERROR, t?.message))

        })
    }
}

}