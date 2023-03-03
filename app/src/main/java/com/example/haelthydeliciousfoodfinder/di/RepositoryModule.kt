package com.example.haelthydeliciousfoodfinder.di

import com.example.haelthydeliciousfoodfinder.data.repositories.RecipeRepositoryImpl
import com.example.haelthydeliciousfoodfinder.domain.repositories.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun bindRecipeReposiory(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository
}