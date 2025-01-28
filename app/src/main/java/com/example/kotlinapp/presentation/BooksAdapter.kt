package com.example.kotlinapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.OnBookClickListener
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books

class BooksAdapter(private val listener: OnBookClickListener) :
    ListAdapter<Books, BooksAdapter.BooksViewHolder>(BooksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = getItem(position)
        Log.d("DDD", "Binding book: $book")
        holder.bind(book, listener)
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Books, listener: OnBookClickListener) {
            itemView.findViewById<TextView>(R.id.bookTitle).text = book.title
            itemView.findViewById<TextView>(R.id.bookAuthor).text = book.author
            itemView.findViewById<CardView>(R.id.bookCardView).setOnClickListener {
                Log.e("DDD", "clicked on item $itemView")
                listener.onBookClick(book)
                Log.e("DDD", "clicked on item $book")
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