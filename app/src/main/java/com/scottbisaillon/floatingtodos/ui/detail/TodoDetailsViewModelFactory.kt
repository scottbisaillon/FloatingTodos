package com.scottbisaillon.floatingtodos.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scottbisaillon.floatingtodos.data.TodoRepository

class TodoDetailsViewModelFactory(
    private val todoRepository: TodoRepository,
    private val todoId: String
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun<T: ViewModel> create(modelClass: Class<T>): T {
        return TodoDetailsViewModel(todoRepository, todoId) as T
    }
}