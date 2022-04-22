package com.enigma.task_management.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class Todo(
    @PrimaryKey val id: Int? = null,
    val todo: String,
    val isCompleted: Boolean
)

class InvalidTodoException(message: String): Exception(message)