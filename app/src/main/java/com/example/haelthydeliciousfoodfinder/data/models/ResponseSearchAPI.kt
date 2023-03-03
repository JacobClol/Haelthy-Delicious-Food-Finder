package com.example.haelthydeliciousfoodfinder.data.models

import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem

data class ResponseSearchAPI(
    val hits: List<Hit>
) {
    fun toRecipe(): List<RecipeItem> {
        return hits.map {
            RecipeItem(
                title = it.recipe.label,
                urlImage = it.recipe.image,
                source = it.recipe.source,
                sourceUrl = it.recipe.url,
                calories = it.recipe.calories,
                ingredientLine = it.recipe.ingredientLines,
                yield = it.recipe.yield,
                dietLabels = it.recipe.dietLabels,
                city = LocationProvider.randomCity()
            )
        }
    }
}

data class Hit(
    val recipe: Recipe
)

data class Recipe(
    val calories: Double,
    val dietLabels: List<String>,
    val image: String,
    val ingredientLines: List<String>,
    val label: String,
    val source: String,
    val url: String,
    val yield: Int
)

