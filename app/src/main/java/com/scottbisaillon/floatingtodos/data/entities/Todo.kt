package com.scottbisaillon.floatingtodos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey @ColumnInfo(name = "id") var todoId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: Instant
) {
    fun formattedTimeString(): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.US)
            .withZone(ZoneId.systemDefault())
        return formatter.format(updatedAt)
    }

    override fun toString(): String {
        return "Todo(todoId='$todoId', title='$title', updatedAt=$updatedAt)"
    }
}