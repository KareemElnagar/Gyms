package com.kareem.gyms


val listOfGyms = listOf(
    Gym(1, "Fitness First", "Mall A"),
    Gym(2, "Titan Cross", "Mall B"),
    Gym(3, "Health Haven", "Mall C"),
    Gym(4, "Elite Workout", "Mall D"),
    Gym(5, "Peak Performance", "Mall E"),
)

data class Gym(val id: Int, val name: String, val place: String, var isFavourite: Boolean = false)
