package com.example.theclassicto_doapp.ui.feature.addedit

sealed interface AddEditEvent {
    data class TitleChange(val title: String) : AddEditEvent
    data class DescriptionChange(val description: String) : AddEditEvent
    data object Save : AddEditEvent
}