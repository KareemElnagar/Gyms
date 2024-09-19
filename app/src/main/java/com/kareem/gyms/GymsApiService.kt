package com.kareem.gyms

import retrofit2.http.GET

interface GymsApiService {
    @GET("gyms.json")
    suspend fun getGyms(): List<Gym>

}