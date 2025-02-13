package com.example.kotlinapp.data

import androidx.room.*

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(book: Books)

    @Delete
    suspend fun removeFromFavorites(book: Books)

    @Query("SELECT * FROM favorite_books")
    suspend fun getAllFavorites(): List<Books>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_books WHERE id = :bookId)")
    suspend fun isFavorite(bookId: Int): Boolean
}