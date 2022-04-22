package com.enigma.task_management.feature_todo.domain.use_case

import com.enigma.task_management.feature_todo.domain.model.InvalidTodoException
import com.enigma.task_management.feature_todo.domain.model.Todo
import com.enigma.task_management.feature_todo.domain.repository.TodoRepository

class AddTodo(private val repository: TodoRepository) {

    suspend operator fun invoke(todo: Todo) {
        if (todo.todo.isBlank()) throw InvalidTodoException("The task of the todo can't be empty")
        repository.insertTodo(todo)
    }

}