package com.example.kotlinapp

import com.example.kotlinapp.data.Books
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("books")
    suspend fun getBooks(): Response<List<Books>>

    @PUT("books/{id}")
    suspend fun updateBook(
        @Path("id") id: Int,
        @Body updatedBook: Books
    ): Response<Books>
}