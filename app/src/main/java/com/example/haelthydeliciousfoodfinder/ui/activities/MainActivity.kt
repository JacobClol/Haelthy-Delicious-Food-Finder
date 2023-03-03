package com.example.haelthydeliciousfoodfinder.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haelthydeliciousfoodfinder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_HaelthyDeliciousFoodFinder_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}