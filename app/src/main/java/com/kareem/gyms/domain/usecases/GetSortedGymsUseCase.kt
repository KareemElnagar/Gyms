package com.kareem.gyms.domain.usecases

import com.kareem.gyms.Gym
import com.kareem.gyms.GymsRepository

class GetSortedGymsUseCase {
    private val gymsRepository = GymsRepository()
    suspend operator fun invoke(): List<Gym> {
        return gymsRepository.getGyms().sortedBy { it.name }
    }
}