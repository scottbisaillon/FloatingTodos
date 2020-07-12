package com.scottbisaillon.floatingtodos.data

class Repository (private val todoDao: TodoDao) {

    val todoList = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }
}