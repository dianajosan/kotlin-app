package com.example.kotlinapp

import com.example.kotlinapp.data.ApiResponse
import com.example.kotlinapp.data.Books
import retrofit2.Response

class ApiClient(
    private val apiService: ApiService
) {
    suspend fun getBooks(): ApiResponse<List<Books>> {
        return safeApiCall { apiService.getBooks() }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): ApiResponse<T> {
        return try {
            ApiResponse.success(apiCall.invoke())
        }catch (e:Exception){
            ApiResponse.failure(e)
        }
    }
}