package com.scottbisaillon.floatingtodos.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.Repository
import com.scottbisaillon.floatingtodos.data.Todo

class TodoListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    val todoList: LiveData<List<Todo>>

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = Repository(todoDao)
        todoList = repository.todoList
    }
}