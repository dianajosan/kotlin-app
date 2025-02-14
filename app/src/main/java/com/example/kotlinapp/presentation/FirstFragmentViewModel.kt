package com.example.kotlinapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.data.ApiResponse
import com.example.kotlinapp.data.Books
import com.example.kotlinapp.domain.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    // LiveData for exposing the books list
    private val _books = MutableLiveData<ApiResponse<List<Books>>>()
    val books: LiveData<ApiResponse<List<Books>>> get() = _books

    // LiveData for error messages
    private val _errorEvent = MutableLiveData<String>()
    val errorEvent: LiveData<String> get() = _errorEvent

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            val response = booksRepository.getBooks()
            Log.d("DDD", "Response posted: $response")
            if (response.isSuccessful) {
                _books.postValue(response)
                Log.d("DDD", "Posting books response: $response")
            }
            // Check if the response is an error
            else {
                _errorEvent.postValue("Error: ${response.exception?.message ?: "Unknown error"}")
            }
        }
    }

    fun updateBook(book: Books, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = booksRepository.updateBook(book)
            if (response.isSuccessful) {
                Log.d("DDD", "Book updated successfully")
                onResult(true)
            } else {
                Log.e("DDD", "Failed to update book: ${response.errorBody()?.string()}")
                onResult(false)
            }
        }
    }

    // Toggle Favorite State
    fun toggleFavorite(book: Books) {
        viewModelScope.launch {
            val isFavorite = booksRepository.isFavorite(book.id)
            if (isFavorite) {
                booksRepository.removeFromFavorites(book)
                Log.e("DDD", "Removed from favorites: ${book.title}")
            } else {
                booksRepository.addToFavorites(book)
                Log.e("DDD", "Added to favorites: ${book.title}")
            }

            // Log updated favorites list
            Log.e("DDD", "Current favorites: ${booksRepository.getAllFavorites()}")
        }
    }

    fun isFavorite(bookId: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isFavorite = booksRepository.isFavorite(bookId)
            callback(isFavorite) // Return the result to update UI
        }
    }
}