package com.kareem.gyms.domain


data class Gym(

    val id: Int,
    val name: String,
    val place: String,
    var isOpen: Boolean,
    val isFavourite: Boolean = false
)
