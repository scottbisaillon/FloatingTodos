package com.scottbisaillon.floatingtodos.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.scottbisaillon.floatingtodos.data.entities.TodoTask

@Dao
interface TodoTaskDao {

    @Insert
    suspend fun insertTodoTasks(todoTasks: List<TodoTask>)

    @Insert
    suspend fun insertTodoTask(todoTask: TodoTask)

    @Update
    suspend fun updateTodoTasks(totoTasks: List<TodoTask>)
}