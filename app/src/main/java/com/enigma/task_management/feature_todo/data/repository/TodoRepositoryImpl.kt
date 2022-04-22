package com.enigma.task_management.feature_todo.data.repository

import com.enigma.task_management.feature_todo.data.data_source.TodoDao
import com.enigma.task_management.feature_todo.domain.model.Todo
import com.enigma.task_management.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> = todoDao.getTodos()

    override suspend fun getTodoById(id: Int): Todo? = todoDao.getTodoById(id)

    override suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}