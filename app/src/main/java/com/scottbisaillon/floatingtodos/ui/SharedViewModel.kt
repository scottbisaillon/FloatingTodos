package com.scottbisaillon.floatingtodos.ui

import androidx.lifecycle.ViewModel
import com.scottbisaillon.floatingtodos.utilities.SingleLiveEvent
import com.scottbisaillon.floatingtodos.utilities.VoidEvent

class SharedViewModel : ViewModel() {
    val navigateUpEvent: SingleLiveEvent<VoidEvent> = SingleLiveEvent()
}