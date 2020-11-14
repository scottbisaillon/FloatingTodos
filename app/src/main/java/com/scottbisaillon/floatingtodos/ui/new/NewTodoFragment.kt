package com.scottbisaillon.floatingtodos.ui.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import com.scottbisaillon.floatingtodos.databinding.NewTodoFragmentBinding
import com.scottbisaillon.floatingtodos.extensions.hideKeyboard
import com.scottbisaillon.floatingtodos.ui.BaseFragment
import com.scottbisaillon.floatingtodos.ui.common.AddNewTaskAdapter
import com.scottbisaillon.floatingtodos.ui.common.TodoTaskAdapter
import java.util.*

class NewTodoFragment : BaseFragment() {
    private val viewModel: NewTodoViewModel by viewModels()
    private lateinit var todoTaskAdapter: TodoTaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NewTodoFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.btnSave.setOnClickListener {
            hideKeyboard()
            viewModel.insertTodo(
                title = binding.todoTitle.text.toString(),
                todoTaskAdapter.currentList
            )
            Navigation.findNavController(it).popBackStack()
        }

        todoTaskAdapter = TodoTaskAdapter(viewModel::removeTask)
        val addNewTaskAdapter = AddNewTaskAdapter { viewModel.addNewTask() }
        binding.taskList.adapter = ConcatAdapter(
            todoTaskAdapter,
            addNewTaskAdapter
        )

        viewModel.todoTaskList.observe(viewLifecycleOwner) { todoTaskList ->
            todoTaskAdapter.submitList(ArrayList(todoTaskList))
        }

        return binding.root
    }
}