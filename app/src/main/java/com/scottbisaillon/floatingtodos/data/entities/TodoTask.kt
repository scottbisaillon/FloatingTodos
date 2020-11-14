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
data class TodoTask(
    @PrimaryKey @ColumnInfo(name = "id") var taskId: String,
    @ColumnInfo(name = "todoId") var todoId: Long?,
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
}