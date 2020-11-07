package com.scottbisaillon.floatingtodos.ui.common

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.data.entities.TodoTask
import com.scottbisaillon.floatingtodos.databinding.TaskListItemBinding
import com.scottbisaillon.floatingtodos.extensions.hideKeyboard

class TaskViewHolder(private val binding: TaskListItemBinding, private val removeTask: (todoTask: TodoTask) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(todoTask: TodoTask) {
        binding.todoTask = todoTask
        binding.todoTaskDescription.setOnEditorActionListener { textView, i, keyEvent ->
            when (i) {
                EditorInfo.IME_ACTION_DONE -> {
                    textView.clearFocus()
                }
            }
            hideKeyboard()
            true
        }
        binding.removeButton.setOnClickListener { removeTask.invoke(todoTask) }
    }
}