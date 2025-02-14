package com.example.kotlinapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinapp.ApiService
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: FirstFragmentViewModel by viewModels()

    private var bookId: Int? = null
    private var originalTitle: String? = null
    private var originalAuthor: String? = null

    private lateinit var bookTitleEditText: EditText
    private lateinit var bookAuthorEditText: EditText
    private lateinit var saveButton: Button

    @Inject
    lateinit var apiService: ApiService // Inject your ApiService here

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI elements
        bookTitleEditText = view.findViewById(R.id.detailsBookTitle)
        bookAuthorEditText = view.findViewById(R.id.detailsBookAuthor)
        saveButton = view.findViewById(R.id.editBookButton)

        // Retrieve book details from arguments
        arguments?.let {
            bookId = it.getInt("bookId")
            originalTitle = it.getString("bookTitle")
            originalAuthor = it.getString("bookAuthor")
        }

        // Populate UI with received data
        bookTitleEditText.setText(originalTitle)
        bookAuthorEditText.setText(originalAuthor)

        // Initially disable the save button
        saveButton.isEnabled = false

        // Add text watchers to detect changes
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isTitleChanged = bookTitleEditText.text.toString() != originalTitle
                val isAuthorChanged = bookAuthorEditText.text.toString() != originalAuthor
                saveButton.isEnabled = isTitleChanged || isAuthorChanged
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        bookTitleEditText.addTextChangedListener(textWatcher)
        bookAuthorEditText.addTextChangedListener(textWatcher)

        // Set up button click listener
        saveButton.setOnClickListener {
            saveBookDetails()
        }
    }

    private fun saveBookDetails() {
        val updatedTitle = bookTitleEditText.text.toString()
        val updatedAuthor = bookAuthorEditText.text.toString()

        if (updatedTitle.isEmpty() || updatedAuthor.isEmpty()) {
            Toast.makeText(requireContext(), "All fields must be filled", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedBook = Books(id = bookId!!, title = updatedTitle, author = updatedAuthor)

        // Call ViewModel to update the book
        viewModel.updateBook(updatedBook) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Book updated successfully!", Toast.LENGTH_SHORT)
                    .show()

                // Close details fragment (navigate back to FirstFragment)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.first_fragment_container, FirstFragment())
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Failed to update book.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}