package com.example.haelthydeliciousfoodfinder.di

import com.example.haelthydeliciousfoodfinder.data.services.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchServiceModule {

    @Provides
    @Singleton
    fun provideServiceBySearch(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}