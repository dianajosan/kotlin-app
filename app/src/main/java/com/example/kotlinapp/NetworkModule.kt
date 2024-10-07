package com.example.kotlinapp

import com.example.kotlinapp.domain.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    fun provideBookRepository(apiService: ApiService): BooksRepository {
        return BooksRepository(apiService)
    }
}