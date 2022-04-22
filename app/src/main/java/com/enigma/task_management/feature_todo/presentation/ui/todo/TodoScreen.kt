package com.enigma.task_management.feature_todo.presentation.ui.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enigma.task_management.feature_todo.base.BaseViewModelFactory
import com.enigma.task_management.feature_todo.data.data_source.TodoDatabase
import com.enigma.task_management.feature_todo.data.repository.TodoRepositoryImpl
import com.enigma.task_management.feature_todo.domain.repository.TodoRepository
import com.enigma.task_management.feature_todo.domain.use_case.*
import com.enigma.task_management.feature_todo.presentation.ui.todo.components.AddEditTodo
import com.enigma.task_management.feature_todo.presentation.ui.todo.components.TodoItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TodoScreen() {
    val context = LocalContext.current

    val viewModel: TodoViewModel = viewModel(factory = BaseViewModelFactory {
        val db = TodoDatabase.getDatabase(context)
        val repository: TodoRepository = TodoRepositoryImpl(db.todoDao)
        val todoUseCases = TodoUseCases(
            addTodo = AddTodo(repository),
            getTodo = GetTodo(repository),
            getTodos = GetTodos(repository),
            deleteTodo = DeleteTodo(repository)
        )
        TodoViewModel(todoUseCases)
    })

    val todos = viewModel.state.value.todos
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Task Management",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            WelcomeText()
            AddEditTodo(todoViewModel = viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(todos) { todo ->
                    TodoItem(
                        todo = todo,
                        onDeleteClick = { viewModel.onEvent(TodoEvent.DeleteTodo(todo)) },
                        modifier = Modifier.clickable {
                            viewModel.onEvent(TodoEvent.GetTodo(todo.id))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }
}

@Composable
fun WelcomeText(name: String = "Rifqi") {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Selamat Datang,")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = name, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}