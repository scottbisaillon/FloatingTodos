package com.scottbisaillon.floatingtodos.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.data.Todo
import com.scottbisaillon.floatingtodos.databinding.TodoListItemBinding

class TodoListAdapter : ListAdapter<Todo, RecyclerView.ViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoViewHolder(
            TodoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        (holder as TodoViewHolder).bind(current)
    }


    class TodoViewHolder(private val binding: TodoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.todo?.let { todo ->
                    navigateToTodo(todo, it)
                }
            }
        }

        private fun navigateToTodo(todo: Todo, view: View) {
            val direction = todo.todoId?.let {
                TodoListFragmentDirections.actionTodoListFragmentToTodoDetailsFragment(
                    it
                )
            }

            if (direction != null) {
                view.findNavController().navigate(direction)
            }
        }

        fun bind(item: Todo) {
            binding.apply {
                todo = item
                executePendingBindings()
            }
        }
    }
}

private class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.todoId == newItem.todoId
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}