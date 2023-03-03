package com.example.haelthydeliciousfoodfinder.ui.fragments

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.haelthydeliciousfoodfinder.data.services.SearchService
import com.example.haelthydeliciousfoodfinder.ui.di.stubGetRecipe
import com.example.haelthydeliciousfoodfinder.ui.pages.SearchFragmentPage
import com.example.haelthydeliciousfoodfinder.utils.Page.Companion.on
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @Inject lateinit var searchService: SearchService

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldSeeWelcomeTitleWhenAfterCreateSearchFragement(){
        searchService.stubGetRecipe()
        on<SearchFragmentPage>()
            .launchFragmentView()
            .checkDisplayTitle()
    }
}