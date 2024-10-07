package com.example.kotlinapp.presentation

import androidx.lifecycle.ViewModel
import com.example.kotlinapp.domain.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    fun fetchBooks() {
        booksRepository.getBooks { result ->
            // Handle result of getting books list
        }
    }
}