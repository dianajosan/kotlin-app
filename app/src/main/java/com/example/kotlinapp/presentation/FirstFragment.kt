package com.example.kotlinapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinapp.R
import com.example.kotlinapp.domain.BooksRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() {

    @Inject
    lateinit var booksRepository: BooksRepository

    private val viewModel: FirstFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Logic to get data from the repository
        viewModel.fetchBooks()

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // Call the repository function
        booksRepository.getBooks { result ->
            result.onSuccess { books ->
                Log.d("DDD", "GET Response: $books")
            }.onFailure { error ->
                Log.e("DDD", "GET Request Failed: $error")
            }
        }

        return view

    }
}
