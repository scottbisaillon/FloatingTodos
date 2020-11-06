package com.scottbisaillon.floatingtodos.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoWithTasks

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Transaction
    @Query("SELECT * FROM todos where id = :todoId")
    fun getTodoWithTasks(todoId: String): LiveData<TodoWithTasks>

    @Query("SELECT * FROM todos where id = :todoId")
    fun getTodo(todoId: String): LiveData<Todo>

    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>
}