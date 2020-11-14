package com.scottbisaillon.floatingtodos.data

import androidx.lifecycle.LiveData
import com.scottbisaillon.floatingtodos.data.daos.TodoDao
import com.scottbisaillon.floatingtodos.data.daos.TodoTaskDao
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.data.entities.TodoWithTasks

class TodoRepository(private val todoDao: TodoDao, private val todoTaskDao: TodoTaskDao) {

    val todoList = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo): Long {
        return todoDao.insert(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.update(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo)
    }

    fun getTodoWithTasks(todoId: Long): LiveData<TodoWithTasks> = todoDao.getTodoWithTasks(todoId)

    suspend fun insertTodoTasks(todoTask: List<TodoTask>) {
        todoTaskDao.insert(todoTask)
    }

    suspend fun updateTodoTasks(todoTasks: List<TodoTask>) {
        todoTaskDao.insertOrUpdate(todoTasks)
    }

    suspend fun deleteTodoTasks(vararg todoTask: TodoTask?) {
        todoTaskDao.delete(*todoTask)
    }

    companion object {
        @Volatile
        private var instance: TodoRepository? = null

        fun getInstance(todoDao: TodoDao, todoTaskDao: TodoTaskDao) =
            instance ?: synchronized(this) {
                instance ?: TodoRepository(todoDao, todoTaskDao).also { instance = it }
            }
    }
}