package com.example.kotlinapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val viewModel: FirstFragmentViewModel by viewModels()
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        booksAdapter = BooksAdapter()
        Log.d("DDD", "Adapter initialized: $booksAdapter")
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = booksAdapter
        Log.d("DDD", "RecyclerView adapter set.")
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.books.observe(viewLifecycleOwner) { response ->
            Log.d("DDD", "Observed response: $response")

            if (response.isSuccessful) {
                val booksList = response.body
                Log.d("DDD", "Submitting books list to adapter: $booksList")
                booksAdapter.submitList(booksList)
            } else {
                Log.e("DDD", "Error fetching books: ${response.exception}")
            }
        }
    }

    // observe pt celalat eveniment
}
