package com.scottbisaillon.floatingtodos.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey @ColumnInfo (name = "id") var todoId: String,
    @ColumnInfo(name = "title") val title: String
) {
    override fun toString(): String {
        return "Todo(todoId='$todoId', title='$title')"
    }
}