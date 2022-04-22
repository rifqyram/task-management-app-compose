package com.enigma.task_management.feature_todo.domain.use_case

data class TodoUseCases(
    val addTodo: AddTodo,
    val getTodos: GetTodos,
    val getTodo: GetTodo,
    val deleteTodo: DeleteTodo
)