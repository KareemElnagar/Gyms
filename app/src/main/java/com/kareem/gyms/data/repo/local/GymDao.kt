package com.kareem.gyms.data.repo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GymDao {

    @Query("SELECT * FROM gyms")
    fun getAll(): List<LocalGym>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(gyms: List<LocalGym>)

    @Update(entity = LocalGym::class)
    fun update(localGymFavouriteState: LocalGymFavouriteState)

    @Query("SELECT * FROM gyms WHERE is_favourite = 1")
    fun getFavouriteGyms(): List<LocalGym>

     fun updateAll(gymsStates: List<LocalGymFavouriteState>) {

    }
}