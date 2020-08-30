package com.scottbisaillon.floatingtodos.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.scottbisaillon.floatingtodos.utilities.Event
import com.scottbisaillon.floatingtodos.utilities.VoidEvent

fun <T> LiveData<out Event<T>>.observeEvent(owner: LifecycleOwner, onEventUnhandled: (T) -> Unit) {
    observe(owner, Observer { it?.getContentIfNotHandled()?.let(onEventUnhandled) })
}

fun LiveData<out VoidEvent>.observeEvent(owner: LifecycleOwner, onEventUnhandled: () -> Unit) {
    observe(owner, Observer { if (!it.hasBeenHandled()) onEventUnhandled() })
}