package com.kareem.gyms.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kareem.gyms.data.repo.remote.GymsApiService
import com.kareem.gyms.domain.Gym
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val state = mutableStateOf<Gym?>(null)
    private var apiService: GymsApiService

    init {
        val url = "https://gyms-49db3-default-rtdb.firebaseio.com/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()

        apiService = retrofit.create(GymsApiService::class.java)
        val gymID = savedStateHandle.get<Int>("gym_id") ?: 0
        getGym(gymID)

    }

    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gym = getGymFromRemoteDB(id)
            state.value = gym
        }
    }

    private suspend fun getGymFromRemoteDB(id: Int) = withContext(Dispatchers.IO) {
        apiService.getGym(id).values.first().let {
            Gym(
                id = it.id,
                name = it.name,
                place = it.place,
                isOpen = it.isOpen,
            )
        }
    }
}