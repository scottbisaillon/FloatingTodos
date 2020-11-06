package com.scottbisaillon.floatingtodos.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.data.entities.TodoTask

data class TodoWithTasks(
    @Embedded val todo: Todo,
    @Relation(
        parentColumn = "id",
        entityColumn = "todoId"
    )
    val tasks: MutableList<TodoTask>
)