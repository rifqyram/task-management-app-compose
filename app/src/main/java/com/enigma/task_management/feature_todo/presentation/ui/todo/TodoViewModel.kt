package com.enigma.task_management.feature_todo.presentation.ui.todo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigma.task_management.feature_todo.domain.model.InvalidTodoException
import com.enigma.task_management.feature_todo.domain.model.Todo
import com.enigma.task_management.feature_todo.domain.repository.TodoRepository
import com.enigma.task_management.feature_todo.domain.use_case.TodoUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TodoViewModel(private val todoUseCases: TodoUseCases) : ViewModel() {

    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state

    private var getTodosJob: Job? = null

    private var currentTodoId: Int? = null

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getTodos()
    }

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.EnteredTextTodo -> {
                _state.value = state.value.copy(text = event.value)
            }
            is TodoEvent.EnteredIsCompletedTodo -> {
                _state.value = state.value.copy(isTodoCompleted = event.value)
            }
            is TodoEvent.SaveTodo -> {
                viewModelScope.launch {
                    try {
                        todoUseCases.addTodo(
                            Todo(
                                id = currentTodoId,
                                todo = state.value.text,
                                isCompleted = state.value.isTodoCompleted
                            )
                        )
                        _state.value = state.value.copy(text = "", isTodoCompleted = false)
                        currentTodoId = null
                    } catch (e: InvalidTodoException) {
                        Log.e("Add Todo", "onEvent: ${e.message}", )
                        _eventFlow.emit(UIEvent.ShowSnackBar(
                            message = e.message ?: "Couldn't save todo"
                        ))
                    }
                }
            }
            is TodoEvent.ClearText -> {
                _state.value = state.value.copy(text = "", isTodoCompleted = false)
            }
            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch {
                    todoUseCases.deleteTodo(event.todo)
                }
            }
            is TodoEvent.GetTodo -> {
                viewModelScope.launch {
                    val todo = event.id?.let { todoUseCases.getTodo(it) }
                    if (todo != null) {
                        currentTodoId = todo.id
                        _state.value = state.value.copy(
                            text = todo.todo,
                            isTodoCompleted = todo.isCompleted
                        )
                    }
                }
            }
        }
    }

    private fun getTodos() {
        getTodosJob?.cancel()
        getTodosJob = todoUseCases.getTodos()
            .onEach { todos ->
                _state.value = state.value.copy(todos = todos)
            }
            .launchIn(viewModelScope)
    }
}

sealed class UIEvent {
    data class ShowSnackBar(val message: String): UIEvent()
}