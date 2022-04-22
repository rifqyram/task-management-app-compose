package com.enigma.task_management.feature_todo.domain.repository

import com.enigma.task_management.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Todo?

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

}