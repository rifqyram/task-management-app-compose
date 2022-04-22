package com.enigma.task_management.feature_todo.presentation.ui.todo

import com.enigma.task_management.feature_todo.domain.model.Todo

data class TodoState(
    val todos: List<Todo> = emptyList(),
    val text: String = "",
    val isTodoCompleted: Boolean = false
)