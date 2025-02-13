package com.example.kotlinapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.data.Books
import com.example.kotlinapp.domain.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesFragmentViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    // LiveData for exposing the favorite books list
    private val _favoriteBooks = MutableLiveData<List<Books>>()
    val favoriteBooks: LiveData<List<Books>> get() = _favoriteBooks

    init {
        fetchFavoriteBooks()
    }

    private fun fetchFavoriteBooks() {
        viewModelScope.launch {
            // Get all favorite books from Room
            val books = booksRepository.getAllFavorites()
            _favoriteBooks.postValue(books)
        }
    }

    // Toggle the favorite status of a book
    fun toggleFavorite(book: Books) {
        viewModelScope.launch {
            booksRepository.removeFromFavorites(book)
            fetchFavoriteBooks()
        }
    }
}