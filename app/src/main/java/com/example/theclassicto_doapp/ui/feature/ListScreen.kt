package com.example.theclassicto_doapp.ui.feature

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theclassicto_doapp.domain.ToDo
import com.example.theclassicto_doapp.domain.todoListItems
import com.example.theclassicto_doapp.ui.components.ToDoItem
import com.example.theclassicto_doapp.ui.theme.TheClassicTODOAPPTheme

@Composable
fun ListScreen() {
    ListContent(emptyList())
}

@Composable
fun ListContent(
    toDos: List<ToDo> = emptyList()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
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
                    onCompletedChange = { toDo.isDone },
                    onItemClick = {},
                    onDeleteClick = {}
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
        ListContent(todoListItems)
    }

}