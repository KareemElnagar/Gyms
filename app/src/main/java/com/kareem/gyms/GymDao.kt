package com.kareem.gyms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GymDao {

    @Query("SELECT * FROM gyms")
     fun getAll(): List<Gym>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addAll(gyms: List<Gym>)
    @Update(entity = Gym::class)
     fun update(gymFavouriteState: GymFavouriteState)
}