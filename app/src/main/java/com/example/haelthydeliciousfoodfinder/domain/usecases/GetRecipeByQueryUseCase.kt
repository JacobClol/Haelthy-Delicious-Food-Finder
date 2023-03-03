package com.example.haelthydeliciousfoodfinder.domain.usecases

import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams
import com.example.haelthydeliciousfoodfinder.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetRecipeByQueryUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(params: RecipeParams): List<RecipeItem> {
        return recipeRepository.getRecipesByQuery(params)
    }
}