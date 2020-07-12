package com.scottbisaillon.floatingtodos.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>
}