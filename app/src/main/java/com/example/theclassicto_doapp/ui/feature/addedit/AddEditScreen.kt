package com.example.theclassicto_doapp.ui.feature.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theclassicto_doapp.data.repository.ToDoRepositoryImpl
import com.example.theclassicto_doapp.data.room.ToDoDataBaseProvider
import com.example.theclassicto_doapp.ui.UiEvent
import com.example.theclassicto_doapp.ui.theme.TheClassicTODOAPPTheme

@Composable
fun AddEditScreen(
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current.applicationContext
    val dataBase = ToDoDataBaseProvider.provide(context)
    val repository = ToDoRepositoryImpl(dataBase.toDoDao)
    val viewModel = viewModel<AddEditViewModel> {
        AddEditViewModel(repository)
    }

    val title = viewModel.title
    val description = viewModel.description

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateBack ->  { navigateBack() }

                is UiEvent.ShowSnackBar -> {
                   snackBarHostState.showSnackbar(
                       message = event.message,
                       withDismissAction = true
                   )
                }

                is UiEvent.Navigate<*> -> {  }
            }
        }
    }

    AddEditContent(
        title = title,
        description = description,
        snackBarHostState = snackBarHostState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    snackBarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(AddEditEvent.Save)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = { title ->
                    onEvent(
                        AddEditEvent.TitleChange(title)
                    )
                },
                placeholder = {
                    Text(text = "Title")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = description ?: "",
                onValueChange = { description ->
                    onEvent(
                        AddEditEvent.DescriptionChange(description)
                    )

                },
                placeholder = {
                    Text(text = "Description (optional)")
                }
            )
        }
    }

}

@Preview
@Composable
private fun AddEditContentPreview() {
    TheClassicTODOAPPTheme {
        AddEditContent(
            title = "",
            description = null,
            snackBarHostState = remember { SnackbarHostState() },
            onEvent = {}
        )
    }
}