package com.vinaylogics.restappkotlin.model

/**
 * Created by DATAPPS on 10-10-2016.
 */

interface ApiCallBack<T> {

    fun onSuccess(model: T?)

    fun onError(model: T?, error: APIError?)
}
