package com.example.haelthydeliciousfoodfinder.data.datasources

import com.example.haelthydeliciousfoodfinder.data.models.Hit
import com.example.haelthydeliciousfoodfinder.data.models.Recipe
import com.example.haelthydeliciousfoodfinder.data.models.ResponseSearchAPI
import com.example.haelthydeliciousfoodfinder.data.models.SearchParams
import com.example.haelthydeliciousfoodfinder.data.services.SearchService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RecipeRemoteDataSourceImplTest {
    @RelaxedMockK
    private lateinit var searchService: SearchService

    private lateinit var recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl

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

    private val searchParamsMock = SearchParams(
        type = "public",
        query = "salad mix",
        appId = "asd231dd",
        appKey = "54dssd3as83kasd",
        diet = "balanced",
        healt = "gluten-free"
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        recipeRemoteDataSourceImpl = RecipeRemoteDataSourceImpl(searchService)
    }

    @Test
    fun `Given SearchParams when getRecipeByQuery is called then should call getItems from service and get ResponseSearchAPI`() = runBlocking {
        //Given
        coEvery {
            searchService.getItems(
                searchParamsMock.type,
                searchParamsMock.query,
                searchParamsMock.appId,
                searchParamsMock.appKey,
                searchParamsMock.diet,
                searchParamsMock.healt
            )
        } returns responseSearchAPIMock

        //When
        val response = recipeRemoteDataSourceImpl.getRecipeByQuery(searchParamsMock)

        //Then
        coVerify(exactly = 1) {
            searchService.getItems(
                searchParamsMock.type,
                searchParamsMock.query,
                searchParamsMock.appId,
                searchParamsMock.appKey,
                searchParamsMock.diet,
                searchParamsMock.healt
            )
        }

        assert(response == responseSearchAPIMock)
    }

}