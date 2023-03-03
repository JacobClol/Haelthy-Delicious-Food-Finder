package com.example.haelthydeliciousfoodfinder.data.datasources

import com.example.haelthydeliciousfoodfinder.data.models.ResponseSearchAPI
import com.example.haelthydeliciousfoodfinder.data.models.SearchParams
import com.example.haelthydeliciousfoodfinder.data.services.SearchService
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService
): RecipeRemoteDataSource {
    override suspend fun getRecipeByQuery(params: SearchParams): ResponseSearchAPI {
        return searchService.getItems(
            params.type,
            params.query,
            params.appId,
            params.appKey,
            params.diet,
            params.healt
        )
    }
}