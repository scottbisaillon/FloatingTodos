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
    val todoTaskList: MutableLiveData<MutableList<TodoTask>> = MutableLiveData()

    fun updateTodo(newTitle: String) = viewModelScope.launch(Dispatchers.IO) {
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

    fun updateTodoTasks(todoTasks: List<TodoTask>) = viewModelScope.launch(Dispatchers.IO) {
        todoTaskList.value?.let {
            todoRepository.updateTodoTasks(todoTasks)
        }
    }

    fun addNewTask() = viewModelScope.launch(Dispatchers.IO) {
        todoWithTasks.value?.let {
            todoRepository.insertTodoTask(
                TodoTask(
                    completedAt = null,
                    todoId = it.todo.todoId,
                    completed = false,
                    description = ""
                )
            )
        }
    }

    fun removeTask(todoTask: TodoTask?) = viewModelScope.launch(Dispatchers.IO) {
        todoWithTasks.value?.let {
            todoTask?.let { it1 -> todoRepository.deleteTodoTask(it1) }
        }
    }
}