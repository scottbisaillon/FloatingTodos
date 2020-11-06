package com.scottbisaillon.floatingtodos.ui.new

import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.databinding.TaskListItemBinding
import java.time.Instant

class TaskViewHolder(private val binding: TaskListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(todoTask: TodoTask) {
        binding.todoTask = todoTask
    }
}