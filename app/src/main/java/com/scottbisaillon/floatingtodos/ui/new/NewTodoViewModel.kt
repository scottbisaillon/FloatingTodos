package com.scottbisaillon.floatingtodos.ui.new

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        todoRepository = TodoRepository.getInstance(todoDao)
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.insertTodo(todo)
    }
}