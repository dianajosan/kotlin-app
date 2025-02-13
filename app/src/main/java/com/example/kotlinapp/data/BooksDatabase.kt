package com.example.kotlinapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Books::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao
}