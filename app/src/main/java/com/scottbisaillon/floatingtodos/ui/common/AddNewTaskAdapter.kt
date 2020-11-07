package com.scottbisaillon.floatingtodos.ui.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AddNewTaskAdapter(private val addTask: () -> Unit): RecyclerView.Adapter<AddNewTaskViewHolder>() {

    init {
//        setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: AddNewTaskViewHolder, position: Int) {
        // There is nothing to do here
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddNewTaskViewHolder {
        return AddNewTaskViewHolder.create(parent, addTask)
    }

    override fun getItemViewType(position: Int): Int = 0

    override fun getItemCount(): Int = 1
}