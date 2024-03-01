package com.example.todolist.home.deleted

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class DeletedViewModel(
    private val repository: TaskRepository,
) : ViewModel() {

    private val tasksFlow = MutableSharedFlow<List<String>>()
    fun getTasksFlow(): Flow<List<String>> = tasksFlow

    fun getList(){
        viewModelScope.launch {
            val tasks = repository.getDeletedTasks()
            tasksFlow.emit(tasks)
        }
    }
}