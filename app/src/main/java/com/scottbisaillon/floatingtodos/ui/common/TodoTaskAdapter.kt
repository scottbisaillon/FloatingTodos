package com.scottbisaillon.floatingtodos.ui.common

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.databinding.TaskListItemBinding
import java.util.*

class TodoTaskAdapter(
    private val updateTask: ((todoTask: TodoTask) -> Unit)?,
    private val removeTask: (todoTask: TodoTask) -> Unit
) :
    ListAdapter<TodoTask, TodoTaskAdapter.ViewHolder>(TASK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TaskListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), updateTask, removeTask
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoTask = getItem(position)
        if (todoTask != null) {
            holder.bind(todoTask)
        }
    }

    class ViewHolder(
        private val binding: TaskListItemBinding,
        private val updateTask: ((todoTask: TodoTask) -> Unit)?,
        private val removeTask: (todoTask: TodoTask) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.todoTaskDescription.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

                override fun afterTextChanged(p0: Editable?) {
                    binding.todoTask?.let {
                        updateTask?.invoke(it)
                    }
                }
            })

            binding.removeButton.setOnClickListener {
                binding.todoTask?.let {
                    removeTask.invoke(it)
                }
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