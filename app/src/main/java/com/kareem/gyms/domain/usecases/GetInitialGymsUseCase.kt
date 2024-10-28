package com.kareem.gyms.domain.usecases

import com.kareem.gyms.Gym
import com.kareem.gyms.GymsRepository

class GetInitialGymsUseCase {
    private val gymsRepository = GymsRepository()
    private val getSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke() : List<Gym> {
        gymsRepository.loadGyms()
        return getSortedGymsUseCase()
    }

}