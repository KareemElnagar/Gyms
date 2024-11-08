package com.kareem.gyms.data.repo.remote

import com.kareem.gyms.domain.Gym
import retrofit2.http.GET
import retrofit2.http.Query

interface GymsApiService {
    @GET("gyms.json")
    suspend fun getGyms(): List<RemoteGym>

    @GET("gyms.json?orderBy=\"id\"")
    suspend fun getGym(
        @Query("equalTo") id : Int
    ) : Map<String, Gym>

}