package com.example.haelthydeliciousfoodfinder.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule(order = 0)
    var rul: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var detailViewModel: DetailViewModel

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
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Given savedStateHandle with RecipeItem by safe arg when DetailViewModel is init then should show data in view`() =
        runTest {
            //Given
            val savedStateHandle = SavedStateHandle().apply {
                set("recipe", recipeItemMock)
            }

            //When init viewmodel
            detailViewModel = DetailViewModel(savedStateHandle)

            //Then
            assert(savedStateHandle.get<RecipeItem>("recipe") == recipeItemMock)
            assert(detailViewModel.error.value == null)
            assert(detailViewModel.recipe.value == recipeItemMock)
        }

    @Test
    fun `Given savedStateHandle with null by safe arg when DetailViewModel is init and then should show an error`() =
        runTest {
            //Given
            val savedStateHandle = SavedStateHandle().apply {
                set("recipe", null)
            }
            //When init viewmodel
            detailViewModel = DetailViewModel(savedStateHandle)

            //Then
            assert(savedStateHandle.get<RecipeItem>("recipe") == null)
            assert(detailViewModel.error.value == "Not found recipe")
            assert(detailViewModel.recipe.value == null)
        }
}