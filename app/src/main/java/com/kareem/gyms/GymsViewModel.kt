package com.kareem.gyms

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kareem.gyms.domain.usecases.GetInitialGymsUseCase
import com.kareem.gyms.domain.usecases.ToggleFavouriteStateUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class GymsViewModel : ViewModel() {
    private var _state by mutableStateOf(
        GymsScreenState(
            gyms = emptyList(),
            isLoading = true
        )
    )
    val state: State<GymsScreenState>
        get() = derivedStateOf { _state }

    private val getAllGymsUseCase = GetInitialGymsUseCase()
    private val  toggleFavouriteStateUseCase = ToggleFavouriteStateUseCase()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

//    private val job = Job()
//    private val scope = CoroutineScope(job + Dispatchers.IO)

    init {
        getGyms()
    }

    private fun getGyms() {
        viewModelScope.launch(errorHandler) {
            val receivedGyms = getAllGymsUseCase()
            _state = _state.copy(
                gyms = receivedGyms,
                isLoading = false
            )

        }
    }

    fun toggleFavouriteState(gymId: Int) {
        val gyms = _state.gyms.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id == gymId }
        viewModelScope.launch {
            val updatedGymsList = toggleFavouriteStateUseCase(gymId,gyms[itemIndex].isFavourite)
            _state = _state.copy(
                gyms = updatedGymsList
            )
        }
    }


}