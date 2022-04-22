package com.enigma.task_management.feature_todo.domain.use_case

import com.enigma.task_management.feature_todo.domain.repository.TodoRepository

class GetTodo(private val todoRepository: TodoRepository) {

    suspend operator fun invoke(id: Int) = todoRepository.getTodoById(id)

}