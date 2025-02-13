package com.example.theclassicto_doapp.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.theclassicto_doapp.data.repository.ToDoRepository
import com.example.theclassicto_doapp.navigation.AddEditRoute
import com.example.theclassicto_doapp.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ToDoRepository,
) : ViewModel() {

    private val addEditRoute = savedStateHandle.toRoute<AddEditRoute>()

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        addEditRoute.id?.let {
            viewModelScope.launch {
                repository.getBy(it)?.let { toDo ->
                    title = toDo.title
                    description = toDo.description
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.TitleChange -> {
                title = event.title
            }

            is AddEditEvent.DescriptionChange -> {
                description = event.description
            }

            is AddEditEvent.Save -> {
                saveToDo()
            }
        }
    }

    private fun saveToDo() {
        viewModelScope.launch {
            if (title.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackBar("The title can't be empty"))
                return@launch
            }

            repository.insert(title, description, addEditRoute.id)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }


}