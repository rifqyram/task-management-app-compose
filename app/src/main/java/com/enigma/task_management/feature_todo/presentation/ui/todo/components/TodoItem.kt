package com.enigma.task_management.feature_todo.presentation.ui.todo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.enigma.task_management.feature_todo.domain.model.Todo
import com.enigma.task_management.feature_todo.presentation.ui.todo.TodoEvent
import com.enigma.task_management.feature_todo.presentation.ui.todo.TodoViewModel

@Composable
fun TodoItem(modifier: Modifier = Modifier, todo: Todo, onDeleteClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = todo.todo,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
            IconButton(onClick = { onDeleteClick() }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }
    }

}
