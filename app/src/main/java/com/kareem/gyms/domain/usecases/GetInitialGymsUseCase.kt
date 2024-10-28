package com.kareem.gyms.domain.usecases

import com.kareem.gyms.domain.Gym
import com.kareem.gyms.data.repo.GymsRepository

class GetInitialGymsUseCase {
    private val gymsRepository = GymsRepository()
    private val getSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke() : List<Gym> {
        gymsRepository.loadGyms()
        return getSortedGymsUseCase()
    }

}