package com.example.kotlinapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.OnBookClickListener
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesFragmentViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesBooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter with FavouritesFragmentViewModel
        favoritesAdapter = FavoritesBooksAdapter(viewModel = viewModel)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = favoritesAdapter

        observeFavoriteBooks()
    }

    private fun observeFavoriteBooks() {
        viewModel.favoriteBooks.observe(viewLifecycleOwner) { booksList ->
            favoritesAdapter.submitList(booksList) // Update the adapter with favorite books
        }
    }
}