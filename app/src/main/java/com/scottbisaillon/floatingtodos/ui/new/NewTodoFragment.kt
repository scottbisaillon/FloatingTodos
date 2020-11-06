package com.scottbisaillon.floatingtodos.ui.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import com.scottbisaillon.floatingtodos.data.entities.Todo
import com.scottbisaillon.floatingtodos.databinding.NewTodoFragmentBinding
import com.scottbisaillon.floatingtodos.extensions.hideKeyboard
import com.scottbisaillon.floatingtodos.ui.BaseFragment
import java.time.Instant
import java.util.*

class NewTodoFragment : BaseFragment() {

    private val viewModel: NewTodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NewTodoFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = TaskAdapter()

        binding.btnSave.setOnClickListener {
            hideKeyboard()

            viewModel.insertTodo(
                Todo(
                    todoId = UUID.randomUUID().toString(),
                    title = binding.todoTitle.text.toString(),
                    updatedAt = Instant.now()
                ),
                adapter.currentList
            )
            Navigation.findNavController(it).popBackStack()
        }

        var addNewTaskAdapter: AddNewTaskAdapter = AddNewTaskAdapter { viewModel.addNewTask() }
        binding.taskList.adapter = ConcatAdapter(
            adapter,
            addNewTaskAdapter
        )

        viewModel.todoTaskList.observe(viewLifecycleOwner) { todoTaskList ->
            // This seems hacky...
            adapter.submitList(ArrayList(todoTaskList))
            binding.taskList.scrollToPosition(adapter.itemCount - 1)
        }

        return binding.root
    }

    override fun onNavigateUp() {
        Toast.makeText(context, "NewTodoFragment, onNavigateUp", Toast.LENGTH_LONG).show()
    }
}