package com.kareem.gyms.domain.usecases

import com.kareem.gyms.domain.Gym
import com.kareem.gyms.data.repo.GymsRepository

class GetSortedGymsUseCase {
    private val gymsRepository = GymsRepository()
    suspend operator fun invoke(): List<Gym> {
        return gymsRepository.getGyms().sortedBy { it.name }
    }
}