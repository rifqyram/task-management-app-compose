package com.enigma.task_management.feature_todo.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enigma.task_management.feature_todo.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}