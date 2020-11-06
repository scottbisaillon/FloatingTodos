package com.scottbisaillon.floatingtodos.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

class TodoDetailsViewModel(private val todoRepository: TodoRepository, todoId: String) : ViewModel() {
    val todo = todoRepository.getTodo(todoId)

    val todoTaskList: MutableLiveData<MutableList<TodoTask>> = MutableLiveData()

    val todoWithTasks = todoRepository.getTodoWithTasks(todoId)

    init {
        todoTaskList.value = todoWithTasks.value?.tasks
        if (todoTaskList.value == null) {
            todoTaskList.value = mutableListOf()
        }
    }

    fun updateTodo(newTitle: String) = viewModelScope.launch(Dispatchers.IO) {
        todo.value?.let {
            if (it.title != newTitle) {
                todoRepository.updateTodo(Todo(todoId = it.todoId, title = newTitle, updatedAt = Instant.now()))
            }
        }
    }

    fun addNewTask() {
        todoWithTasks.value?.let {
            todoTaskList.value?.add(TodoTask(taskId = UUID.randomUUID().toString(), completedAt = null, todoId = it.todo.todoId, completed = false, description = ""))
            todoTaskList.value = todoTaskList.value;
        }
    }
}