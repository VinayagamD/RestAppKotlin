package com.vinaylogics.restappkotlin.model

/**
 * Created by yousefabed on 10/11/16.
 */
data class APIError(var code: Int, var message: String?) {
    companion object {

        val VALIDATION_ERROR = 1
        val SERVER_ERROR = 2
        val RECORD_NOT_FOUND_ERROR = 3
        val FORMAT_ERROR = 4
        val CONNECTION_ERROR = 5
        val NOT_AUTHORIZED = 6
    }
}
