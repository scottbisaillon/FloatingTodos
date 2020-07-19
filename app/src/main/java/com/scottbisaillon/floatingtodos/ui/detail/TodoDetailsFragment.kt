package com.scottbisaillon.floatingtodos.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.scottbisaillon.floatingtodos.R
import com.scottbisaillon.floatingtodos.databinding.TodoDetailsFragmentBinding
import com.scottbisaillon.floatingtodos.extensions.hideKeyboard
import com.scottbisaillon.floatingtodos.utilities.InjectorUtils

class TodoDetailsFragment : Fragment() {

    private val TAG = "TodoDetailsFragment"

    private val args: TodoDetailsFragmentArgs by navArgs()

    private val todoDetailsViewModel: TodoDetailsViewModel by viewModels {
        InjectorUtils.provideTodoDetailsViewModelFactory(requireActivity(), args.todoId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<TodoDetailsFragmentBinding>(
            inflater, R.layout.todo_details_fragment, container, false
        ).apply {
            viewModel = todoDetailsViewModel
            lifecycleOwner = viewLifecycleOwner

            todoTitle.setOnEditorActionListener { view, i, _ ->
                when (i) {
                    EditorInfo.IME_ACTION_DONE -> {
                        todoDetailsViewModel.updateTodo(view.text.toString())
                    }
                }
                hideKeyboard()
                true
            }
        }

        return binding.root
    }
}