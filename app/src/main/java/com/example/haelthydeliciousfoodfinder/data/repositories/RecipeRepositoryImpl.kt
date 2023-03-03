package com.example.haelthydeliciousfoodfinder.data.repositories

import com.example.haelthydeliciousfoodfinder.data.datasources.RecipeRemoteDataSource
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams
import com.example.haelthydeliciousfoodfinder.domain.repositories.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource
): RecipeRepository {
    override suspend fun getRecipesByQuery(params: RecipeParams): List<RecipeItem> {
        return remoteDataSource.getRecipeByQuery(params.toSearchParams()).toRecipe()
    }
}