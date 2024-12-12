package com.example.kotlinapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.domain.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            val response = booksRepository.getBooks()
            Log.d("DDD", "GET Response: $response")

        }
    }
}