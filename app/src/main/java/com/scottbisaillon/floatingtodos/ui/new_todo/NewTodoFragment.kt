package com.scottbisaillon.floatingtodos.ui.new_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.scottbisaillon.floatingtodos.R

class NewTodoFragment : Fragment() {

    private val viewModel: NewTodoViewModel by viewModels()

    companion object {
        fun newInstance() = NewTodoFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_todo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}