package com.scottbisaillon.floatingtodos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.scottbisaillon.floatingtodos.extensions.observeEvent

abstract class BaseFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.navigateUpEvent.observeEvent(this) {
            onNavigateUp()
        }
    }

    open fun onNavigateUp() {}
}