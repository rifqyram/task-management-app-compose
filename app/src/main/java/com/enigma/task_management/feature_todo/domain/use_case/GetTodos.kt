package com.enigma.task_management.feature_todo.domain.use_case

import com.enigma.task_management.feature_todo.domain.repository.TodoRepository

class GetTodos(private val todoRepository: TodoRepository) {

    operator fun invoke() = todoRepository.getTodos()
}