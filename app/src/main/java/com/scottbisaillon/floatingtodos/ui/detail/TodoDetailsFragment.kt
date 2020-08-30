package com.scottbisaillon.floatingtodos.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.scottbisaillon.floatingtodos.R
import com.scottbisaillon.floatingtodos.databinding.TodoDetailsFragmentBinding
import com.scottbisaillon.floatingtodos.extensions.hideKeyboard
import com.scottbisaillon.floatingtodos.ui.BaseFragment
import com.scottbisaillon.floatingtodos.utilities.InjectorUtils

class TodoDetailsFragment : BaseFragment() {

    private val TAG = "TodoDetailsFragment"

    private val args: TodoDetailsFragmentArgs by navArgs()

    private lateinit var binding: TodoDetailsFragmentBinding;

    private val todoDetailsViewModel: TodoDetailsViewModel by viewModels {
        InjectorUtils.provideTodoDetailsViewModelFactory(requireActivity(), args.todoId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            todoDetailsViewModel.updateTodo(binding.todoTitle.text.toString())
            findNavController().navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<TodoDetailsFragmentBinding>(
            inflater, R.layout.todo_details_fragment, container, false
        ).apply {
            viewModel = todoDetailsViewModel
            lifecycleOwner = viewLifecycleOwner

            todoTitle.clearFocus()

            // TODO: Add a text watcher to determine if any data has changed
            todoTitle.setOnEditorActionListener { view, i, _ ->
                when (i) {
                    EditorInfo.IME_ACTION_DONE -> {
                        todoDetailsViewModel.updateTodo(view.text.toString())
                        todoTitle.clearFocus()
                    }
                }
                hideKeyboard()
                true
            }

            

            todoTitle.clearFocus()
        }

        return binding.root
    }

    override fun onNavigateUp() {
        // TODO: If any data has changed and has not been committed, show a confirmation dialog
        todoDetailsViewModel.updateTodo(binding.todoTitle.text.toString())
        hideKeyboard()
    }
}