package com.example.haelthydeliciousfoodfinder.ui.fragments

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.haelthydeliciousfoodfinder.ui.pages.DetailFragmentPage
import com.example.haelthydeliciousfoodfinder.utils.Page.Companion.on
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldSeeTitleRecipeWhenAfterCreateDetailFragement(){
        on<DetailFragmentPage>()
            .launchFragmentView()
            .checkDisplayTitle()
    }
}