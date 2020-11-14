package com.scottbisaillon.floatingtodos.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.data.entities.TodoWithTasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

class TodoDetailsViewModel(private val todoRepository: TodoRepository, todoId: Long) :
    ViewModel() {
    val todoWithTasks: LiveData<TodoWithTasks> = todoRepository.getTodoWithTasks(todoId)

    fun save(newTitle: String) {
        updateTodo(newTitle)
    }

    fun addNewTask() = viewModelScope.launch(Dispatchers.IO) {
        todoWithTasks.value?.let {
            todoRepository.insertTodoTask(
                TodoTask(
                    taskId = UUID.randomUUID().toString(),
                    completedAt = null,
                    todoId = it.todo.todoId,
                    completed = false,
                    description = ""
                )
            )
        }
    }

    fun removeTask(todoTask: TodoTask) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.deleteTodoTasks(todoTask)
    }

    private fun updateTodo(newTitle: String) = viewModelScope.launch(Dispatchers.IO) {
        todoWithTasks.value?.let {
            if (it.todo.title != newTitle) {
                todoRepository.updateTodo(
                    Todo(
                        todoId = it.todo.todoId,
                        title = newTitle,
                        updatedAt = Instant.now()
                    )
                )
            }
        }
    }

    fun updateTodoTask(todoTask: TodoTask) = viewModelScope.launch(Dispatchers.IO) {
        todoTask.let {
            todoRepository.updateTodoTask(it)
        }
    }
}