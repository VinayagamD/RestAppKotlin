package com.vinaylogics.restappkotlin.api

import android.util.Log

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


/**
 * Created by Yousef Abed

 */

object ServiceGenerator {
    val API_BASE_URL = "https://jsonplaceholder.typicode.com/"


    private val httpClient = OkHttpClient.Builder()


    private val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)

    fun <S> createService(serviceClass: Class<S>): S {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag("okhttp").d(message)  })
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BASIC
        logging.level = HttpLoggingInterceptor.Level.BODY
        Log.i(ServiceGenerator::class.java.name, "Interceptor list size size:" + httpClient.interceptors().size)
        httpClient.interceptors().clear()
        httpClient.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        })
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)


        builder.addConverterFactory(GsonConverterFactory.create())
        httpClient.addInterceptor(logging)
        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

}