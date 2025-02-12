package com.example.theclassicto_doapp.ui

sealed interface UiEvent {

    data object NavigateBack : UiEvent

    data class Navigate<T : Any>(val route: T) : UiEvent
    data class ShowSnackBar(val message: String) : UiEvent
}