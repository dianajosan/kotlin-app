package com.example.kotlinapp

import com.example.kotlinapp.data.BooksDao
import com.example.kotlinapp.domain.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        apiService: ApiService,
        booksDao: BooksDao // Let Hilt inject BooksDao here
    ): BooksRepository {
        return BooksRepository(apiService, booksDao)
    }
}