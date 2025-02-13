package com.example.kotlinapp

import com.example.kotlinapp.data.Books

interface OnBookClickListener {

    // pass the click events from the ViewHolder to the parent fragment
    fun onBookClick(book: Books)
    fun onFavoriteClick(book: Books, isFavorite: Boolean)
}