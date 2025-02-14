package com.example.kotlinapp.domain

import RetrofitClient.apiClient
import android.util.Log
import com.example.kotlinapp.ApiService
import com.example.kotlinapp.data.BooksDao
import com.example.kotlinapp.data.ApiResponse
import com.example.kotlinapp.data.Books
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val apiService: ApiService,
    private val booksDao: BooksDao
) {

    // Make a network request to fetch books from the API
    suspend fun getBooks(): ApiResponse<List<Books>> {
        val response = apiClient.getBooks()
        Log.d("DDD", "Fetched books from API: : $response")
        return response
    }

    suspend fun updateBook(book: Books): Response<Books> {
        val response = apiService.updateBook(book.id, book)
        return response
    }

    // Add book to favorites
    suspend fun addToFavorites(book: Books) {
        booksDao.addToFavorites(book)
    }

    // Remove book from favorites
    suspend fun removeFromFavorites(book: Books) {
        booksDao.removeFromFavorites(book)
    }

    // Get all favorite books
    suspend fun getAllFavorites(): List<Books> {
        return booksDao.getAllFavorites()
    }

    // Check if a book is a favorite
    suspend fun isFavorite(bookId: Int): Boolean {
        return booksDao.isFavorite(bookId)
    }
}