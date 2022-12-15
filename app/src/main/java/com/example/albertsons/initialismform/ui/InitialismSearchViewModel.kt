package com.example.albertsons.initialismform.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertsons.initialismform.models.domain.InitialismItem
import com.example.albertsons.initialismform.models.ui.InitialismUiState
import com.example.albertsons.initialismform.usecase.GetInitialismUseCase
import com.example.albertsons.initialismform.usecase.PrepareInitialismResultForUiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class InitialismTSearchViewModel @Inject constructor(
    private val getInitialismUseCase: GetInitialismUseCase,
    private val prepareInitialismResultForUiUseCase: PrepareInitialismResultForUiUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<InitialismUiState>()
    val uiState: LiveData<InitialismUiState> = _uiState
    fun onSfChanged(sf: String) {
        viewModelScope.launch {
            _uiState.value = InitialismUiState.Loading
            try {
                val domainData = getInitialismUseCase(sf)
                notifyUsersLoaded(domainData)
            } catch (e: Throwable) {
                yield()
                _uiState.value = InitialismUiState.Error
                Log.e("InitialismSearchView", e.message.orEmpty()) // Ususally we use timber
            }

        }
    }

    private suspend fun notifyUsersLoaded(usersList: List<InitialismItem>) {
        val result = prepareInitialismResultForUiUseCase(usersList)
        _uiState.value = InitialismUiState.InitialismResultLoaded(result)
    }

    companion object {
        const val DEBOUNCE = 300L
    }
}