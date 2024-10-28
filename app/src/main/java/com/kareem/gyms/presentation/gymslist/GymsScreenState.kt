package com.kareem.gyms.presentation.gymslist

import com.kareem.gyms.domain.Gym

data class GymsScreenState(
    val gyms : List<Gym>,
    val isLoading : Boolean,
    val error : String? = null
)
