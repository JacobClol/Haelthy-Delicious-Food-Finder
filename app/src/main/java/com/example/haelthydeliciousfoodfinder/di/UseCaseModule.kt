package com.example.haelthydeliciousfoodfinder.di

import com.example.haelthydeliciousfoodfinder.domain.repositories.RecipeRepository
import com.example.haelthydeliciousfoodfinder.domain.usecases.GetRecipeByQueryUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Reusable
    fun provideGetRecipeByQueryUseCase(
        recipeRepository: RecipeRepository
    ) = GetRecipeByQueryUseCase(recipeRepository)

}