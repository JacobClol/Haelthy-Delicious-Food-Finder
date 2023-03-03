package com.example.haelthydeliciousfoodfinder.data.models

class LocationProvider {
    companion object{
        fun randomCity(): String{
            val position: Int = (0..10).random()
            return cities[position]
        }

        private val cities = arrayListOf(
            "Dorćol, Belgrade, Serbia",
            "Sao Pablo, Brazil",
            "Rome, Italy",
            "Paris, France",
            "London, United Kingdom",
            "Madrid, Spain",
            "Berlin, Germany",
            "Chicago, United State",
            "New York, United State",
            "Raquira, Colombia",
            "Sidney, Australia"
        )
    }
}