package com.example.kotlinapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books

class FavoritesBooksAdapter(
    private val viewModel: FavouritesFragmentViewModel
) : ListAdapter<Books, FavoritesBooksAdapter.FavoriteBooksViewHolder>(BooksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return FavoriteBooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteBooksViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, viewModel)
    }

    class FavoriteBooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
        private val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)
        private val favoritesButton: ImageButton = itemView.findViewById(R.id.favourites_button)

        fun bind(
            book: Books,
            viewModel: FavouritesFragmentViewModel
        ) {
            bookTitle.text = book.title
            bookAuthor.text = book.author

            // Set the favorite state to true for favorites fragment
            favoritesButton.setImageResource(R.drawable.ic_heart_filled)

            // Handle remove from favorites
            favoritesButton.setOnClickListener {
                Log.e("DDD", "Removed book from favorites")
                Toast.makeText(itemView.context, "${bookTitle.text} removed from favorites", Toast.LENGTH_SHORT).show()

                viewModel.toggleFavorite(book)
            }
        }
    }

    class BooksDiffCallback : DiffUtil.ItemCallback<Books>() {
        override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem == newItem
        }
    }
}