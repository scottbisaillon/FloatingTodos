package com.scottbisaillon.floatingtodos.ui.new

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant

import java.util.*

class NewTodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository
    val todoTaskList: MutableLiveData<MutableList<TodoTask>> = MutableLiveData()
    private val todoId = UUID.randomUUID().toString()

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        val todoTaskDao = AppDatabase.getDatabase(application).todoTaskDao()
        todoRepository = TodoRepository.getInstance(todoDao, todoTaskDao)
        todoTaskList.value = mutableListOf()
    }

    fun insertTodo(title: String, todoTasks: List<TodoTask>) =
        viewModelScope.launch(Dispatchers.IO) {
            val id = todoRepository.insertTodo(Todo(title = title, updatedAt = Instant.now()))
            todoRepository.insertTodoTasks(todoTasks.map {
                TodoTask(
                    taskId = it.taskId,
                    completedAt = it.completedAt,
                    todoId = id,
                    completed = it.completed,
                    description = it.description
                )
            })
        }

    fun addNewTask() {
        todoTaskList.value?.add(
            TodoTask(
                taskId = UUID.randomUUID().toString(),
                completedAt = null,
                todoId = -1, //TODO: Figure out a better placeholder
                completed = false,
                description = ""
            )
        )
        todoTaskList.value = todoTaskList.value;
    }

    fun removeTask(todoTask: TodoTask?) {
        todoTaskList.value?.remove(todoTask)
        todoTaskList.value = todoTaskList.value
    }
}