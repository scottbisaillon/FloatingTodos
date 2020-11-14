package com.scottbisaillon.floatingtodos.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoWithTasks

@Dao
abstract class TodoDao : BaseDao<Todo>() {
    @Transaction
    @Query("SELECT * FROM todos where id = :todoId")
    abstract fun getTodoWithTasks(todoId: Long): LiveData<TodoWithTasks>

    @Query("SELECT * FROM todos where id = :todoId")
    abstract fun getTodo(todoId: Long): LiveData<Todo>

    @Query("SELECT * FROM todos")
    abstract fun getAllTodos(): LiveData<List<Todo>>
}