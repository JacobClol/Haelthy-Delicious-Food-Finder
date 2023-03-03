package com.example.haelthydeliciousfoodfinder.ui.pages

import android.os.Bundle
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.haelthydeliciousfoodfinder.R
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.ui.fragments.DetailFragment
import com.example.haelthydeliciousfoodfinder.utils.Page
import com.example.haelthydeliciousfoodfinder.utils.isTextDisplayed
import com.example.haelthydeliciousfoodfinder.utils.launchFragmentInHiltContainer

class DetailFragmentPage : Page() {

    fun launchFragmentView() : DetailFragmentPage {
        val bundle = Bundle()
        bundle.putParcelable("recipe", recipeItemMock)
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.mobile_navigation)
        navController.setCurrentDestination(R.id.detailFragment)
        launchFragmentInHiltContainer<DetailFragment>(fragmentArgs = bundle, navController = navController)
        return this
    }

    fun checkDisplayTitle(): DetailFragmentPage {
        isTextDisplayed("Quinoa Salda")
        return this
    }
}

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