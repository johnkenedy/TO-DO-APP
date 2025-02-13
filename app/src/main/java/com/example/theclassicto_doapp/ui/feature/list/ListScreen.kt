package com.example.theclassicto_doapp.ui.feature.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.theclassicto_doapp.domain.ToDo
import com.example.theclassicto_doapp.domain.todoListItems
import com.example.theclassicto_doapp.navigation.AddEditRoute
import com.example.theclassicto_doapp.ui.UiEvent
import com.example.theclassicto_doapp.ui.components.ToDoItem
import com.example.theclassicto_doapp.ui.theme.TheClassicTODOAPPTheme

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    navigateToAddEditScreen: (id: Long?) -> Unit
) {

    val toDos by viewModel.toDos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when(uiEvent.route) {
                        is AddEditRoute -> {
                            navigateToAddEditScreen(uiEvent.route.id)
                        }
                    }
                }
                is UiEvent.NavigateBack -> {

                }
                is UiEvent.ShowSnackBar -> {

                }
            }
        }
    }

    ListContent(
        toDos = toDos,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ListContent(
    toDos: List<ToDo>,
    onEvent: (ListEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ListEvent.AddEdit(null))
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(toDos) { index, toDo ->
                ToDoItem(
                    toDo = toDo,
                    onCompletedChange = {
                        onEvent(ListEvent.IsDoneChanged(toDo.id, it))
                    },
                    onItemClick = {
                        onEvent(ListEvent.AddEdit(toDo.id))
                    },
                    onDeleteClick = {
                        onEvent(ListEvent.Delete(toDo.id))
                    }
                )

                if (index < toDos.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListContentPreview() {
    TheClassicTODOAPPTheme {
        ListContent(
            toDos = todoListItems,
            onEvent = {}
            )
    }

}