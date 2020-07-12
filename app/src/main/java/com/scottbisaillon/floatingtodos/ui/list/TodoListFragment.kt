package com.scottbisaillon.floatingtodos.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.scottbisaillon.floatingtodos.R

class TodoListFragment : Fragment() {

    companion object {
        fun newInstance() = TodoListFragment()
    }

    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.todo_list_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvTodoList)
        val adapter = TodoListAdapter(view.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        viewModel.todoList.observe(viewLifecycleOwner, Observer { todoList ->
            todoList?.let { adapter.setWords(it) }
        })

        view.findViewById<FloatingActionButton>(R.id.fabNewTodo).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_todoListFragment_to_newTodoFragment, null))

        return view
    }
}