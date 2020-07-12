package com.scottbisaillon.floatingtodos.ui.new

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.Repository
import com.scottbisaillon.floatingtodos.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = Repository(todoDao)
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTodo(todo)
    }
}