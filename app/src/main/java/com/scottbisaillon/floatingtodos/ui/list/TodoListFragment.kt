package com.scottbisaillon.floatingtodos.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.scottbisaillon.floatingtodos.R
import com.scottbisaillon.floatingtodos.databinding.TodoListFragmentBinding
import com.scottbisaillon.floatingtodos.ui.BaseFragment

class TodoListFragment : BaseFragment() {

    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = TodoListFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = TodoListAdapter()
        binding.todoList.adapter = adapter

        viewModel.todoList.observe(viewLifecycleOwner) { todoList ->
            adapter.submitList(todoList)
        }

        binding.newTodo.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_todoListFragment_to_newTodoFragment,
                null
            )
        )

        return binding.root
    }
}
