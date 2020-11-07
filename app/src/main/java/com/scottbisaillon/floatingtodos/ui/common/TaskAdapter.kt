package com.scottbisaillon.floatingtodos.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.databinding.TaskListItemBinding

class TaskAdapter : ListAdapter<TodoTask, TaskViewHolder>(TASK_COMPARATOR) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(TaskListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val todoTask = getItem(position)
        if (todoTask != null) {
            holder.bind(todoTask)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object{
        private val TASK_COMPARATOR = object : DiffUtil.ItemCallback<TodoTask>() {
            override fun areItemsTheSame(oldItem: TodoTask, newItem: TodoTask): Boolean =
                oldItem.taskId == newItem.taskId

            override fun areContentsTheSame(oldItem: TodoTask, newItem: TodoTask): Boolean =
                newItem == oldItem
        }
    }
}