package com.example.todolist.home.deleted

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.repository.TaskRepository

class DeletedFactory (
    private val repository: TaskRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeletedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeletedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}