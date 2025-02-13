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
import com.example.kotlinapp.OnBookClickListener
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(), OnBookClickListener {

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

        booksAdapter = BooksAdapter(this, viewModel)
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
            if (response != null) {
                val booksList = response.body
                Log.d("DDD", "Submitting books list to adapter: $booksList")
                booksAdapter.submitList(booksList)
            }
        }

        // Observe error LiveData
        viewModel.errorEvent.observe(viewLifecycleOwner) { errorMessage ->
            Log.e("DDD", "Observed error: $errorMessage")
            if (errorMessage != null) {
                // Show Snackbar with the error message
                Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onBookClick(book: Books) {
        Log.d("DDD", "Clicked Book: id=${book.id}, title=${book.title}, author=${book.author}")

        val bundle = Bundle().apply {
            putInt("bookId", book.id)
            putString("bookTitle", book.title)
            putString("bookAuthor", book.author)
        }

        val detailsFragment = DetailsFragment().apply {
            arguments = bundle
        }
        // Replace fragment transaction
        parentFragmentManager.beginTransaction()
            .replace(R.id.first_fragment_container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onFavoriteClick(book: Books, isFavorite: Boolean) {
        // Call the ViewModel to add/remove the book from favorites in Room
        viewModel.toggleFavorite(book)

        // Update the UI by manually toggling the button state (for real-time feedback)
        val position = (booksAdapter.currentList.indexOf(book))
        if (position != -1) {
            booksAdapter.notifyItemChanged(position)  // Notify the adapter to update only this item
        }
    }
}
