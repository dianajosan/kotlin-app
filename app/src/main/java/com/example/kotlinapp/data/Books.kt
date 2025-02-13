package com.example.kotlinapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_books")
data class Books (
    @PrimaryKey val id: Int,
    val title: String,
    val author: String
)