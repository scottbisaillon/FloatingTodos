package com.scottbisaillon.floatingtodos.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.scottbisaillon.floatingtodos.R

class AddNewTaskViewHolder(view: View, add: () -> Unit) : RecyclerView.ViewHolder(view) {

    private val add: Button = itemView.findViewById<Button>(R.id.button).also {
        it.setOnClickListener { add.invoke() }
    }

    companion object {
        fun create(parent: ViewGroup, add: () -> Unit): AddNewTaskViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_footer, parent, false)
            return AddNewTaskViewHolder(view, add)
        }
    }
}