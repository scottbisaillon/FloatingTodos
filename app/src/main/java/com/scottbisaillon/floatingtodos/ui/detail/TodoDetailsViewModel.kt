package com.scottbisaillon.floatingtodos.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.Todo
import com.scottbisaillon.floatingtodos.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoDetailsViewModel(private val todoRepository: TodoRepository, todoId: String) : ViewModel() {
    val todo = todoRepository.getTodo(todoId)

    fun updateTodo(newTitle: String) = viewModelScope.launch(Dispatchers.IO) {
        todo.value?.let {
            if (it.title != newTitle) {
                todoRepository.updateTodo(Todo(todoId = it.todoId, title = newTitle))
            }
        }
    }
}