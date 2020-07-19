package com.scottbisaillon.floatingtodos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val selectedTodoId = MutableLiveData<Number>()

    fun selectTodo(todoId: Number) {
        selectedTodoId.value = todoId
    }
}