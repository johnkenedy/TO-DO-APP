package com.example.theclassicto_doapp.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theclassicto_doapp.data.repository.ToDoRepository
import com.example.theclassicto_doapp.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val repository: ToDoRepository,
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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

            repository.insert(title, description)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }


}