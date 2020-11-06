package com.scottbisaillon.floatingtodos.ui.new

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NewTodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        todoRepository = TodoRepository.getInstance(todoDao)
    }

    fun insertTodo(todo: Todo, todoTasks: List<TodoTask>) = viewModelScope.launch(Dispatchers.IO) {
        todo.todoId = todoId
        todoRepository.insertTodo(todo)
        todoRepository.insertTodoTasks(todoTasks)
    }

    fun addNewTask() {
        todoTaskList.value?.add(
            TodoTask(
                taskId = UUID.randomUUID().toString(),
                completedAt = null,
                todoId = todoId,
                completed = false,
                description = ""
            )
        )
        todoTaskList.value = todoTaskList.value;
    }
}