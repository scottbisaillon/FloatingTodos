package com.scottbisaillon.floatingtodos.utilities

import android.content.Context
import com.scottbisaillon.floatingtodos.data.AppDatabase
import com.scottbisaillon.floatingtodos.data.TodoRepository
import com.scottbisaillon.floatingtodos.ui.detail.TodoDetailsViewModel
import com.scottbisaillon.floatingtodos.ui.detail.TodoDetailsViewModelFactory

object InjectorUtils {
    private fun getTodoRepository(context: Context): TodoRepository {
        return TodoRepository.getInstance(
            AppDatabase.getDatabase(context.applicationContext).todoDao()
        )
    }

    fun provideTodoDetailsViewModelFactory(
        context: Context,
        todoId: String
    ): TodoDetailsViewModelFactory {
        return TodoDetailsViewModelFactory(getTodoRepository(context), todoId)
    }

}