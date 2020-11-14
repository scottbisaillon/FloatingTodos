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
    private val deletedTodoTaskList: MutableList<TodoTask?> = mutableListOf()

    fun addNewTask() {
        todoWithTasks.value?.let {
            todoTaskList.value?.add(
                TodoTask(
                    taskId = UUID.randomUUID().toString(),
                    completedAt = null,
                    todoId = it.todo.todoId,
                    completed = false,
                    description = ""
                )
            )
            todoTaskList.value = todoTaskList.value
        }
    }

    fun removeTask(todoTask: TodoTask?) {
        deletedTodoTaskList.add(todoTask)
        todoTaskList.value?.remove(todoTask)
        todoTaskList.value = todoTaskList.value
    }

    fun save(newTitle: String) {
        updateTodo(newTitle)
        updateTodoTasks()
        deleteTodoTasks()
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

    private fun updateTodoTasks() = viewModelScope.launch(Dispatchers.IO) {
        todoTaskList.value?.let {
            // TODO: Check to see if the contents have actually changed
            todoRepository.updateTodoTasks(it)
        }
    }

    private fun deleteTodoTasks() = viewModelScope.launch(Dispatchers.IO) {
        deletedTodoTaskList.let {
            if (deletedTodoTaskList.isNotEmpty()) {
                todoRepository.deleteTodoTasks(*it.toTypedArray())
            }
        }
    }
}