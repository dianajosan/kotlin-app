package com.example.kotlinapp.domain

import RetrofitClient.apiClient
import android.util.Log
import com.example.kotlinapp.ApiService
import com.example.kotlinapp.data.ApiResponse
import com.example.kotlinapp.data.Books
import javax.inject.Inject

class BooksRepository @Inject constructor(private val apiService: ApiService) {

    // make a network request to fetch books from the API
    suspend fun getBooks(): ApiResponse<List<Books>> {
        val response = apiClient.getBooks()
        Log.d("DDD", "Fetched books from API: : $response")
        return response
    }
}