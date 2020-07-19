package com.scottbisaillon.floatingtodos.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todos where id = :todoId")
    fun getTodo(todoId: String): LiveData<Todo>

    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>
}