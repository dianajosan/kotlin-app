package com.example.kotlinapp.domain

import RetrofitClient
import android.util.Log
import com.example.kotlinapp.ApiService
import com.example.kotlinapp.data.Books
import javax.inject.Inject

class BooksRepository @Inject constructor(private val apiService: ApiService) {

    // make a network request
    suspend fun getBooks(): List<Books>? {
        val request = RetrofitClient.apiClient.getBooks()
        if (request.failed) {
            Log.d("DDD", "FAILED Request: $request")
            return null
        }

        if (request.isSuccessful) {
            Log.d("DDD", "GET Request: $request")
            return request.body
        }
        return null
    }
}