package com.example.haelthydeliciousfoodfinder.di

import com.example.haelthydeliciousfoodfinder.data.datasources.RecipeRemoteDataSource
import com.example.haelthydeliciousfoodfinder.data.datasources.RecipeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Reusable
    abstract fun bindItemRemoteDatasource(
        recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl
    ): RecipeRemoteDataSource
}