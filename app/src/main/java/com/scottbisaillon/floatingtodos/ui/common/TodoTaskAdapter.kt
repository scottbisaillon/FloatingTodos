package com.scottbisaillon.floatingtodos.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.databinding.TaskListItemBinding
import com.scottbisaillon.floatingtodos.databinding.TodoListItemBinding
import kotlinx.coroutines.Job

class TodoTaskAdapter(private val removeTask: (todoTask: TodoTask?) -> Unit) :
    ListAdapter<TodoTask, TodoTaskAdapter.ViewHolder>(TASK_COMPARATOR) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TaskListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), removeTask
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoTask = getItem(position)
        if (todoTask != null) {
            holder.bind(todoTask)
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).taskId!!
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(
        private val binding: TaskListItemBinding,
        private val removeTask: (todoTask: TodoTask?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.removeButton.setOnClickListener {
                removeTask.invoke(binding.todoTask)
            }
        }

        fun bind(item: TodoTask) {
            binding.apply {
                todoTask = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val TASK_COMPARATOR = object : DiffUtil.ItemCallback<TodoTask>() {
            override fun areItemsTheSame(oldItem: TodoTask, newItem: TodoTask): Boolean =
                oldItem.taskId == newItem.taskId

            override fun areContentsTheSame(oldItem: TodoTask, newItem: TodoTask): Boolean =
                newItem == oldItem
        }
    }
}