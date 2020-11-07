package com.scottbisaillon.floatingtodos.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.scottbisaillon.floatingtodos.data.entities.TodoTask

@Dao
interface TodoTaskDao {

    @Delete
    suspend fun deleteTodoTask(todoTask: TodoTask)

    @Insert
    suspend fun insertTodoTasks(todoTasks: List<TodoTask>)

    @Insert
    suspend fun insertTodoTask(todoTask: TodoTask)

    @Update
    suspend fun updateTodoTasks(totoTasks: List<TodoTask>)
}