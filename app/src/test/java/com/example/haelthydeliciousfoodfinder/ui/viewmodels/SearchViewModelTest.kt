package com.example.haelthydeliciousfoodfinder.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams
import com.example.haelthydeliciousfoodfinder.domain.usecases.GetRecipeByQueryUseCase
import com.example.haelthydeliciousfoodfinder.utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    @RelaxedMockK
    private lateinit var getRecipeByQueryUseCase: GetRecipeByQueryUseCase

    private lateinit var searchViewModel: SearchViewModel

    @get:Rule(order = 0)
    var rul: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

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
        query = "Salad mix",
    )


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when SearchViewModel is init then call fetchRecipeByQuery with initial search and return RecipeItem`() =
        runTest {
            //Given
            coEvery { getRecipeByQueryUseCase(recipeParamsMock) } returns listOf(recipeItemMock)

            //When init viewmodel
            searchViewModel = SearchViewModel(getRecipeByQueryUseCase)

            //Then
            coVerify(exactly = 1) {
                getRecipeByQueryUseCase(recipeParamsMock)
            }

            assert(searchViewModel.listRecipesRemote.value == listOf(recipeItemMock))
            assert(searchViewModel.error.value == null)
            assert(searchViewModel.isLoading.value == false)
        }

    @Test
    fun `when SearchViewModel is create and call fetchRecipeByQuery but response is empty then should show an error`() =
        runTest {
            //Given
            coEvery { getRecipeByQueryUseCase(recipeParamsMock) } returns emptyList()

            //When init viewmodel
            searchViewModel = SearchViewModel(getRecipeByQueryUseCase)

            //Then
            coVerify(exactly = 1) {
                getRecipeByQueryUseCase(recipeParamsMock)
            }

            assert(searchViewModel.listRecipesRemote.value == null)
            assert(searchViewModel.error.value == "Not found recipes")
            assert(searchViewModel.isLoading.value == false)
        }

}