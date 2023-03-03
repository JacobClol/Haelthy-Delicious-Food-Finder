package com.example.haelthydeliciousfoodfinder.domain.models

import com.example.haelthydeliciousfoodfinder.data.models.SearchParams

data class RecipeParams(
    val type: String = "public",
    val query: String,
    val appId: String = "63489f7a",
    val appKey: String = "04da33b5238d5337041d4858820375d0",
    val diet: String = "balanced",
    val healt: String = "gluten-free"
) {
    fun toSearchParams() = SearchParams(
        type, query, appId, appKey, diet, healt
    )
}