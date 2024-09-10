package com.kareem.gyms


val listOfGyms = listOf<Gym> (
    Gym("Fitness First", "Mall A"),
    Gym("Titan Cross", "Mall B"),
    Gym("Health Haven", "Mall C"),
    Gym("Elite Workout", "Mall D"),
    Gym("Peak Performance", "Mall E"),
)
data class Gym(val name: String, val place: String)
