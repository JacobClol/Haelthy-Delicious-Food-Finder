package com.example.haelthydeliciousfoodfinder.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItem(
    val title: String,
    val urlImage: String,
    val source: String,
    val sourceUrl: String,
    val calories: Double,
    val ingredientLine: List<String>,
    val yield: Int,
    val dietLabels: List<String>,
    val city: String
) : Parcelable
