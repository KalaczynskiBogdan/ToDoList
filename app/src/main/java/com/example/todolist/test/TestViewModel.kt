package com.example.todolist.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TestViewModel(
    private val repository: TestRepository
):ViewModel() {
    private val namesResultLiveData = MutableLiveData<List<String>>()
    fun getNamesResultLiveData(): LiveData<List<String>> = namesResultLiveData

    fun fetchNames(){
        viewModelScope.launch {
            val result = repository.getNames()
            namesResultLiveData.postValue(result)
        }

    }

}