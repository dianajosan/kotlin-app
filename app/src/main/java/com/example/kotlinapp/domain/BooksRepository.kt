package com.example.kotlinapp.domain

import com.example.kotlinapp.ApiService
import com.example.kotlinapp.data.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(private val apiService: ApiService) {
    fun getBooks(callback: (Result<List<ApiResponse>>) -> Unit) {
        apiService.getBooks().enqueue(object : Callback<List<ApiResponse>> {
            override fun onResponse(
                call: Call<List<ApiResponse>>,
                response: Response<List<ApiResponse>>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    callback(Result.success(responseData ?: listOf()))
                } else {
                    callback(Result.failure(Throwable("GET Request Failed: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }
}