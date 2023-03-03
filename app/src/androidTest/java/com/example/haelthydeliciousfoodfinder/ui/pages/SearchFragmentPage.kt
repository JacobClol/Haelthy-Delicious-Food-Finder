package com.example.haelthydeliciousfoodfinder.ui.pages

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.haelthydeliciousfoodfinder.ui.fragments.SearchFragment
import com.example.haelthydeliciousfoodfinder.R
import com.example.haelthydeliciousfoodfinder.utils.Page
import com.example.haelthydeliciousfoodfinder.utils.isTextDisplayed
import com.example.haelthydeliciousfoodfinder.utils.launchFragmentInHiltContainer

class SearchFragmentPage : Page() {

    fun launchFragmentView() : SearchFragmentPage {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.mobile_navigation)
        navController.setCurrentDestination(R.id.searchFragment)
        launchFragmentInHiltContainer<SearchFragment>(fragmentArgs = null, navController = navController)
        return this
    }

    fun checkDisplayTitle(): SearchFragmentPage {
        isTextDisplayed("Welcome to search healthly recipe in english")
        return this
    }
}