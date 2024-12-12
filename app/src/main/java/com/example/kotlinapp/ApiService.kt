package com.example.kotlinapp

import com.example.kotlinapp.data.Books
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("books")
    suspend fun getBooks(): Response<List<Books>>
}