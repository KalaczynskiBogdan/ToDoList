package com.example.todolist.addtask

import androidx.lifecycle.ViewModel
import com.example.todolist.data.datamodel.Task
import com.example.todolist.repository.TaskRepository

class AddTaskViewModel(
    private val repository: TaskRepository
): ViewModel() {
    fun getInProgressList() : ArrayList<Task>{
        return repository.getInProgressTasks()
    }
    fun addNewTask(task: Task){
        repository.addNewTask(task)
    }
}