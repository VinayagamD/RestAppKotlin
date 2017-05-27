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
class Post(
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
    fun getPost( callBack: ApiCallBack<List<Post>>) {
        val api = ServiceGenerator.createService(PostApi::class.java)
        val call = api.posts
        queueCall(call,callBack)

    }

    private fun queueCall( call: Call<List<Post>> ,  callBack: ApiCallBack<List<Post>>){
        call.enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                if (response?.isSuccessful as Boolean){
                    callBack.onSuccess(response.body() as List<Post>)
                }else{
                    val x : Int = response.code()
                   when (x){
                       401 -> callBack.onError(response.body() as  List<Post> , APIError(APIError.NOT_AUTHORIZED, "Un authorized") )
                       else -> callBack.onError(response.body() as  List<Post> , APIError(APIError.RECORD_NOT_FOUND_ERROR, "Un authorized") )
                   }
                }
            }

             override fun onFailure(call: Call<List<Post>>?, t: Throwable?) = callBack.onError( null , APIError(APIError.CONNECTION_ERROR, t?.message))

        })
    }
}

}