package com.scottbisaillon.floatingtodos.data

import androidx.lifecycle.LiveData

class TodoRepository (private val todoDao: TodoDao) {

    val todoList = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    fun getTodo(todoId: String): LiveData<Todo> = todoDao.getTodo(todoId)

    companion object {

        @Volatile private var instance: TodoRepository? = null

        fun getInstance(todoDao: TodoDao) = instance ?: synchronized(this) {
            instance ?: TodoRepository(todoDao).also { instance = it }
        }
    }


}