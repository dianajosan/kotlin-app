package com.example.kotlinapp

import com.example.kotlinapp.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("books")
    fun getBooks(): Call<List<ApiResponse>>
}