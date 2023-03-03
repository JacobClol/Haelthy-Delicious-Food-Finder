package com.example.haelthydeliciousfoodfinder.domain.usecases

import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams
import com.example.haelthydeliciousfoodfinder.domain.repositories.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRecipeByQueryUseCaseTest {
    @RelaxedMockK
    private lateinit var recipeRepository: RecipeRepository

    private lateinit var getRecipeByQueryUseCase: GetRecipeByQueryUseCase

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

    private val recipeParamsMock = RecipeParams(
        type = "public",
        query = "salad mix",
        appId = "asd231dd",
        appKey = "54dssd3as83kasd",
        diet = "balanced",
        healt = "gluten-free"
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRecipeByQueryUseCase = GetRecipeByQueryUseCase(recipeRepository)
    }

    @Test
    fun `when the getRecipeByQueryUseCase is invoke then call getRecipesByQuery and return to list RecipeItem`() =
        runBlocking {
            //Given
            coEvery { recipeRepository.getRecipesByQuery(recipeParamsMock) } returns listOf(
                recipeItemMock
            )

            //When
            val response = getRecipeByQueryUseCase(recipeParamsMock)

            //Then
            coVerify(exactly = 1) { recipeRepository.getRecipesByQuery(recipeParamsMock) }
            assert(response == listOf(recipeItemMock))
        }

}