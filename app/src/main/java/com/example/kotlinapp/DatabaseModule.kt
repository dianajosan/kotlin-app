package com.example.kotlinapp

import android.content.Context
import androidx.room.Room
import com.example.kotlinapp.data.BooksDao
import com.example.kotlinapp.data.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context,
            BooksDatabase::class.java,
            "books_database"
        ).build()
    }

    @Provides
    fun provideBooksDao(booksDatabase: BooksDatabase): BooksDao {
        return booksDatabase.booksDao()
    }
}