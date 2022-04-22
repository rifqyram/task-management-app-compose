package com.enigma.task_management.feature_todo.presentation.ui.todo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.enigma.task_management.feature_todo.presentation.ui.todo.TodoEvent
import com.enigma.task_management.feature_todo.presentation.ui.todo.TodoViewModel
import com.enigma.task_management.ui.theme.LightRed

@Composable
fun AddEditTodo(modifier: Modifier = Modifier, todoViewModel: TodoViewModel) {
    val todoTextState = todoViewModel.state.value.text
    val todoIsCompleted = todoViewModel.state.value.isTodoCompleted

    Column(modifier = modifier) {
        Column {
            Text(text = "Input Your Task")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = todoTextState,
                onValueChange = {
                    todoViewModel.onEvent(TodoEvent.EnteredTextTodo(it))
                },
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LabelledCheckBox(
                checked = todoIsCompleted,
                onCheckedChange = { todoViewModel.onEvent(TodoEvent.EnteredIsCompletedTodo(it)) },
                label = "Is Completed?"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { todoViewModel.onEvent(TodoEvent.SaveTodo) }) {
                Text(text = "Save Todo")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { todoViewModel.onEvent(TodoEvent.ClearText) }) {
                Text(text = "Clear")
            }
        }
    }
}

@Composable
fun LabelledCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(
                indication = rememberRipple(color = LightRed),
                interactionSource = remember {
                    MutableInteractionSource()
                },
                onClick = { onCheckedChange(!checked) }
            )
            .requiredHeight(ButtonDefaults.MinHeight)
            .padding(4.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange(it) },
            colors = CheckboxDefaults.colors(checkedColor = LightRed)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = label)
    }
}