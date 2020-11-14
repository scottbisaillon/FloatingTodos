package com.scottbisaillon.floatingtodos.data.daos

import androidx.room.Dao
import com.scottbisaillon.floatingtodos.data.entities.TodoTask

@Dao
abstract class TodoTaskDao : BaseDao<TodoTask>()