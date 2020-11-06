package com.scottbisaillon.floatingtodos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Entity(tableName = "tasks")
class TodoTask (
    @PrimaryKey @ColumnInfo(name = "id") var taskId: String,
    @ColumnInfo(name = "todoId") var todoId: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "completed") var completed: Boolean,
    @ColumnInfo(name = "completedAt") var completedAt: Instant?
) {
    fun formattedTimeString(): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.US)
            .withZone(ZoneId.systemDefault())
        return formatter.format(completedAt)
    }

    override fun toString(): String {
        return "TodoTask(taskId='$taskId', description='$description', completed=$completed, completedAt=$completedAt)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TodoTask

        if (taskId != other.taskId) return false
        if (todoId != other.todoId) return false
        if (description != other.description) return false
        if (completed != other.completed) return false
        if (completedAt != other.completedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = taskId.hashCode()
        result = 31 * result + todoId.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + completed.hashCode()
        result = 31 * result + completedAt.hashCode()
        return result
    }
}