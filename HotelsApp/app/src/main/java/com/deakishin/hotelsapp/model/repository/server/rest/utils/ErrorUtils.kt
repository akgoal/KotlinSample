package com.deakishin.hotelsapp.model.repository.server.rest.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/** Utils class for parsing error from a server response. */
class ErrorUtils(retrofit: Retrofit) {

    private val converter: Converter<ResponseBody, ApiError>
            = retrofit.responseBodyConverter(ApiError::class.java, arrayOf())

    /** Parses an error contained in the response. */
    fun parseError(response: retrofit2.Response<*>): ApiError {
        return response.errorBody()?.let { converter.convert(it) }
                ?: ApiError("", "")
    }
}

/** Class containing error info. */
data class ApiError(val statusCode: String, val message: String)