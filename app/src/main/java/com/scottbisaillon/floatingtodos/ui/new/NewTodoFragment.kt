package com.scottbisaillon.floatingtodos.ui.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.scottbisaillon.floatingtodos.R
import com.scottbisaillon.floatingtodos.data.Todo
import com.scottbisaillon.floatingtodos.extensions.hideKeyboard
import java.util.*

class NewTodoFragment : Fragment() {

    private val viewModel: NewTodoViewModel by viewModels()
    private lateinit var etTodoTitle: EditText

    companion object {
        fun newInstance() = NewTodoFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.new_todo_fragment, container, false)

        etTodoTitle = view.findViewById(R.id.etTodoTitle)

        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            hideKeyboard()
            viewModel.insertTodo(Todo(todoId = UUID.randomUUID().toString(), title = etTodoTitle.text.toString()))
            Navigation.findNavController(it).popBackStack()
        }

        return view
    }
}