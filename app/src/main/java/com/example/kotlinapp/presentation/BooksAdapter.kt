package com.example.kotlinapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.data.Books

class BooksAdapter : ListAdapter<Books, BooksAdapter.BooksViewHolder>(BooksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = getItem(position)
        Log.d("DDD", "Binding book: $book")
        holder.bind(book)
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Books) {
            itemView.findViewById<TextView>(R.id.bookTitle).text = book.title
            itemView.findViewById<TextView>(R.id.bookAuthor).text = book.author
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

//    override fun submitList(list: List<Books>?) {
//        Log.d("DDD", "BooksAdapter: submitList called with: $list")
//        super.submitList(list)
//    }
}