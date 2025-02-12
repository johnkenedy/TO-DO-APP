package com.example.theclassicto_doapp.ui.feature.list

sealed interface ListEvent {
    data class Delete(val id: Long) : ListEvent
    data class IsDoneChanged(val id: Long, val isDone: Boolean) : ListEvent
    data class AddEdit(val id: Long?) : ListEvent
}