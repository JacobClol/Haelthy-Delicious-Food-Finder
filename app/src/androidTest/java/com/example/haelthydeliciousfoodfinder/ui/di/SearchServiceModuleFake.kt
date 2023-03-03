package com.example.haelthydeliciousfoodfinder.ui.di

import com.example.haelthydeliciousfoodfinder.data.models.Hit
import com.example.haelthydeliciousfoodfinder.data.models.Recipe
import com.example.haelthydeliciousfoodfinder.data.models.ResponseSearchAPI
import com.example.haelthydeliciousfoodfinder.data.services.SearchService
import com.example.haelthydeliciousfoodfinder.di.SearchServiceModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SearchServiceModule::class]
)
object SearchServiceModuleFake {

    @Provides
    @Singleton
    fun provideSearchService(): SearchService = mockk()
}

fun SearchService.stubGetRecipe() {
    coEvery { getItems(any(), any(), any(), any(), any(), any()) } answers { responseSearchAPIMock }
}

private val responseSearchAPIMock = ResponseSearchAPI(
    hits = listOf(
        Hit(
            Recipe(
                calories = 120.5,
                dietLabels = listOf(
                    "Balanced"
                ),
                image = "http://image.url/recipe",
                ingredientLines = listOf(
                    "1 cup quinoa",
                    "2 cups chicken or vegetable stock",
                    "1 hothouse cucumber, diced",
                    "2 scallions"
                ),
                label = "Quinoa Salad",
                source = "Food Network",
                url = "http://food_network.com/",
                yield = 4
            )
        )
    )
)
