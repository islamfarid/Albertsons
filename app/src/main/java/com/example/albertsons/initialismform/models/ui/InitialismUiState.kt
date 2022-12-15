package com.example.albertsons.initialismform.models.ui

sealed class InitialismUiState {
    class InitialismResultLoaded(val initialismUiModels: List<InitialismUiModel>) : InitialismUiState()
    object Error : InitialismUiState()
    object Loading : InitialismUiState()
}