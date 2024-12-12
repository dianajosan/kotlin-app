package com.example.kotlinapp.data

import retrofit2.Response
import java.lang.Exception

data class ApiResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {
    companion object {
        fun <T> success(data: Response<T>): ApiResponse<T> {
            return ApiResponse(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): ApiResponse<T> {
            return ApiResponse(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status {
        data object Success : Status()
        data object Failure : Status()
    }

    val failed: Boolean
        get() = this.status == Status.Failure
    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true
    val body: T
        get() = this.data!!.body()!!
}
