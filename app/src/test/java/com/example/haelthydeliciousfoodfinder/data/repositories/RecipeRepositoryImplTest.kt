package com.example.haelthydeliciousfoodfinder.data.repositories

import com.example.haelthydeliciousfoodfinder.data.datasources.RecipeRemoteDataSource
import com.example.haelthydeliciousfoodfinder.data.models.Hit
import com.example.haelthydeliciousfoodfinder.data.models.Recipe
import com.example.haelthydeliciousfoodfinder.data.models.ResponseSearchAPI
import com.example.haelthydeliciousfoodfinder.data.models.SearchParams
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class RecipeRepositoryImplTest {
    @RelaxedMockK
    private lateinit var remoteDataSource: RecipeRemoteDataSource

    private lateinit var recipeRepositoryImpl: RecipeRepositoryImpl

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

    private val recipeParamsMock = RecipeParams(
        type = "public",
        query = "salad mix",
        appId = "asd231dd",
        appKey = "54dssd3as83kasd",
        diet = "balanced",
        healt = "gluten-free"
    )

    private val recipeItemMock = RecipeItem(
        calories = 120.5,
        dietLabels = listOf(
            "Balanced"
        ),
        urlImage = "http://image.url/recipe",
        ingredientLine = listOf(
            "1 cup quinoa",
            "2 cups chicken or vegetable stock",
            "1 hothouse cucumber, diced",
            "2 scallions"
        ),
        title = "Quinoa Salad",
        source = "Food Network",
        sourceUrl = "http://food_network.com/",
        yield = 4,
        city = "Rome, Italy"
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        recipeRepositoryImpl = RecipeRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `Given a RecipeParams when getListItemsFromAPI is called then should call getRemoteItemsBySearch return ItemModel`() =
        runBlocking {
            //Given
            coEvery {
                remoteDataSource.getRecipeByQuery(recipeParamsMock.toSearchParams())
            } returns responseSearchAPIMock

            //When
            val response = recipeRepositoryImpl.getRecipesByQuery(recipeParamsMock)

            //Then
            coVerify(exactly = 1) {
                remoteDataSource.getRecipeByQuery(recipeParamsMock.toSearchParams())
            }

            assert(response[0].title == responseSearchAPIMock.toRecipe()[0].title)
            assert(response.size == responseSearchAPIMock.toRecipe().size)
        }

}