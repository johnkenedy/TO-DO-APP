package com.example.theclassicto_doapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theclassicto_doapp.domain.ToDo
import com.example.theclassicto_doapp.domain.todoListItems
import com.example.theclassicto_doapp.ui.theme.TheClassicTODOAPPTheme

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    toDo: ToDo,
    onCompletedChange: (Boolean) -> Unit = {},
    onItemClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = toDo.isDone,
                onCheckedChange = onCompletedChange
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = toDo.title,
                    style = MaterialTheme.typography.titleLarge
                )

                toDo.description?.let {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = toDo.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete"
                )
            }
        }
    }
}

@Preview
@Composable
private fun ToDoItemPreview() {
    TheClassicTODOAPPTheme {
        ToDoItem(
            toDo = todoListItems[0],
            onCompletedChange = {},
            onItemClick = {},
            onDeleteClick = {}
        )
    }
}

@Preview
@Composable
private fun ToDoItemCompletedPreview() {
    TheClassicTODOAPPTheme {
        ToDoItem(
            toDo = todoListItems[1],
            onCompletedChange = {},
            onItemClick = {},
            onDeleteClick = {}
        )
    }
}