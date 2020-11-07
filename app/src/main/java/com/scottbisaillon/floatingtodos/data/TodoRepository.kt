package com.scottbisaillon.floatingtodos.data

import androidx.lifecycle.LiveData
import com.scottbisaillon.floatingtodos.data.daos.TodoDao
import com.scottbisaillon.floatingtodos.data.daos.TodoTaskDao
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.data.entities.TodoWithTasks

class TodoRepository (private val todoDao: TodoDao, private val todoTaskDao: TodoTaskDao) {

    val todoList = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    fun getTodoWithTasks(todoId: String): LiveData<TodoWithTasks> = todoDao.getTodoWithTasks(todoId)

    suspend fun insertTodoTask(todoTask: TodoTask) {
        todoTaskDao.insertTodoTask(todoTask)
    }

    suspend fun insertTodoTasks(todoTask: List<TodoTask>) {
        todoTaskDao.insertTodoTasks(todoTask)
    }

    suspend fun updateTodoTasks(todoTasks: List<TodoTask>) {
        todoTaskDao.updateTodoTasks(todoTasks)
    }

    companion object {

        @Volatile private var instance: TodoRepository? = null

        fun getInstance(todoDao: TodoDao, todoTaskDao: TodoTaskDao) = instance ?: synchronized(this) {
            instance ?: TodoRepository(todoDao, todoTaskDao).also { instance = it }
        }
    }


}