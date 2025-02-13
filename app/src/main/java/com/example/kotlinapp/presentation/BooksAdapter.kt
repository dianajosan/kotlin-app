package com.example.kotlinapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.OnBookClickListener
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books

class BooksAdapter(
    private val listener: OnBookClickListener,
    private val viewModel: FirstFragmentViewModel
) :
    ListAdapter<Books, BooksAdapter.BooksViewHolder>(BooksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = getItem(position)
        Log.d("DDD", "Binding book: $book")

        // Check if the book is in the favorites list
        viewModel.isFavorite(book.id) { isFavorite ->
            holder.bind(book, listener, isFavorite)
        }
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
        private val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)
        private val bookCardView: CardView = itemView.findViewById(R.id.bookCardView)
        private val favoritesButton: ImageButton = itemView.findViewById(R.id.favourites_button)

        fun bind(
            book: Books,
            listener: OnBookClickListener,
            isFavorite: Boolean
        ) {
            bookTitle.text = book.title
            bookAuthor.text = book.author

            // Handle card click
            bookCardView.setOnClickListener {
                Log.e("DDD", "clicked on item $book")
                listener.onBookClick(book)
            }

            // Set button state based on favorite status
            updateFavoriteUI(isFavorite)

            // Toggle favorite state when button is clicked
            favoritesButton.setOnClickListener {
                listener.onFavoriteClick(book, isFavorite)
            }
        }

        private fun updateFavoriteUI(isFavorite: Boolean) {
            favoritesButton.isSelected = isFavorite
            favoritesButton.setImageResource(if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline)
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