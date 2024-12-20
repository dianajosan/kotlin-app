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

    // expose list as LiveData
    private val _books = MutableLiveData<ApiResponse<List<Books>>>()
    val books: LiveData<ApiResponse<List<Books>>> get() = _books

    // val ceva = LiveData<> pt evenimentele de error
    // cand vine eroare, afisam in fragment un snackbar cu mesaj

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            val response = booksRepository.getBooks()
            Log.d("DDD", "Response posted: $response")
            _books.postValue(response)
            Log.d("DDD", "Posting books response: $response")

        }
    }
}