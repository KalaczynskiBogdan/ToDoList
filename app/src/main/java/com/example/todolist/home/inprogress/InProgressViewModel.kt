package com.example.todolist.home.inprogress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.datamodel.Task
import com.example.todolist.repository.TaskRepository
import kotlinx.coroutines.launch

class InProgressViewModel(
    private val repository: TaskRepository,
) : ViewModel() {
    private val tasksResultLiveData: MutableLiveData<ArrayList<Task>> = MutableLiveData()

    fun getTasksResultLiveData(): LiveData<ArrayList<Task>> = tasksResultLiveData

    fun fetchList(){
        viewModelScope.launch {
            val result = repository.getInProgressTasks()
            tasksResultLiveData.postValue(result)
        }
    }
}