package com.scottbisaillon.floatingtodos.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.R
import com.scottbisaillon.floatingtodos.data.Todo

class TodoListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var todos = emptyList<Todo>()

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.tvTodoName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = inflater.inflate(R.layout.todo_list_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todos[position]
        holder.wordItemView.text = current.title
    }

    internal fun setWords(words: List<Todo>) {
        this.todos = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = todos.size
}