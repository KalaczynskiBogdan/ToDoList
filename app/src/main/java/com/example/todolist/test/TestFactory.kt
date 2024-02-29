package com.example.todolist.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TestFactory(
    private val repository: TestRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TestViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}