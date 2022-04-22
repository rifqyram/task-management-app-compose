package com.enigma.task_management.feature_todo.presentation.ui.todo

import com.enigma.task_management.feature_todo.domain.model.Todo

sealed class TodoEvent {
    data class EnteredTextTodo(val value: String): TodoEvent()
    data class EnteredIsCompletedTodo(val value: Boolean): TodoEvent()
    data class DeleteTodo(val todo: Todo): TodoEvent()
    data class GetTodo(val id: Int?): TodoEvent()
    object ClearText: TodoEvent()
    object SaveTodo: TodoEvent()
}