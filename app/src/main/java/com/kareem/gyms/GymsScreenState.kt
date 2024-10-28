package com.kareem.gyms

data class GymsScreenState(
    val gyms : List<Gym>,
    val isLoading : Boolean,
    val error : String? = null
)
