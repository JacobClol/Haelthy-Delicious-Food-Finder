package com.example.haelthydeliciousfoodfinder.data.models

data class SearchParams (
    val type: String = "public",
    val query: String,
    val appId: String,
    val appKey: String,
    val diet: String = "balanced",
    val healt: String = "gluten-free"
)