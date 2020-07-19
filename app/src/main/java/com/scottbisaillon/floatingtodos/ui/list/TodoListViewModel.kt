package com.scottbisaillon.floatingtodos.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.Todo

class TodoListViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository

    val todoList: LiveData<List<Todo>>

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        todoRepository = TodoRepository.getInstance(todoDao)
        todoList = todoRepository.todoList
    }
}