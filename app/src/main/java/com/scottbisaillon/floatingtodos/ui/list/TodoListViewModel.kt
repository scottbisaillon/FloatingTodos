package com.scottbisaillon.floatingtodos.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.entities.Todo

class TodoListViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository

    val todoList: LiveData<List<Todo>>

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        val todoTaskDao = AppDatabase.getDatabase(application).todoTaskDao()
        todoRepository = TodoRepository.getInstance(todoDao, todoTaskDao)
        todoList = todoRepository.todoList
    }
}