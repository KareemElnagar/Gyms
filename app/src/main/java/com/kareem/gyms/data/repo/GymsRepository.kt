package com.kareem.gyms.data.repo

import com.kareem.gyms.GymsApplication
import com.kareem.gyms.data.repo.local.GymsDatabase
import com.kareem.gyms.data.repo.local.LocalGym
import com.kareem.gyms.data.repo.local.LocalGymFavouriteState
import com.kareem.gyms.data.repo.remote.GymsApiService
import com.kareem.gyms.domain.Gym
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsRepository() {
    private val url = "https://gyms-49db3-default-rtdb.firebaseio.com/"
    private val apiService: GymsApiService = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .baseUrl(url)
        .build()
        .create(GymsApiService::class.java)

    private var gymsDao = GymsDatabase.getDaoInstance(GymsApplication.getApplicationContext())

     suspend fun loadGyms() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (ex: Exception) {
            if (gymsDao.getAll().isEmpty()) {
                throw Exception("Something went wrong. We have no data.")
            }

        }

    }

    suspend fun getGyms(): List<Gym> {
         return withContext(Dispatchers.IO) {
             return@withContext gymsDao.getAll().map {
                 Gym(it.id,it.name,it.place,it.isOpen,it.isFavourite)
             }
         }
    }
     suspend fun updateLocalDatabase() {
        val gyms = apiService.getGyms()
        val favouriteGymsList = gymsDao.getFavouriteGyms()
        gymsDao.addAll(
            gyms.map {
                LocalGym(it.id,it.name,it.place,it.isOpen)
            }
        )
        gymsDao.updateAll(
            favouriteGymsList.map {
                LocalGymFavouriteState(it.id, true)
            }
        )
    }
     suspend fun toggleFavouriteGym(gymId: Int, state: Boolean) =
        withContext(Dispatchers.IO) {
            gymsDao.update(
                LocalGymFavouriteState(
                    id = gymId,
                    isFavourite = state
                )
            )
            gymsDao.getAll()
        }
}