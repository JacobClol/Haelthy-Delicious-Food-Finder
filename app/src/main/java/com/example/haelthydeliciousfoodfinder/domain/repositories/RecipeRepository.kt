package com.example.haelthydeliciousfoodfinder.domain.repositories

import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams

interface RecipeRepository {
    suspend fun getRecipesByQuery(params: RecipeParams): List<RecipeItem>
}