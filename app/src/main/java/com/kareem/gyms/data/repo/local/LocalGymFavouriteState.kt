package com.kareem.gyms.data.repo.local

import androidx.room.ColumnInfo
import androidx.room.Entity
@Entity
data class LocalGymFavouriteState(
    @ColumnInfo(name = "gym_id")
    val id: Int,

    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean = false
)