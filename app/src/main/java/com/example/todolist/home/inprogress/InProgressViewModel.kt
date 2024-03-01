package com.example.todolist.home.inprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class InProgressViewModel(
    private val repository: TaskRepository,
) : ViewModel() {
    private val tasksFlow = MutableSharedFlow<List<String>>()
    fun getTasksFlow(): Flow<List<String>> = tasksFlow

    fun getList(){
        viewModelScope.launch {
            val tasks = repository.getInProgressTasks()
            tasksFlow.emit(tasks)
        }
    }
}