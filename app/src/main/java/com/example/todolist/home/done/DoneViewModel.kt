package com.example.todolist.home.done

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.repository.TaskRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DoneViewModel (
    private val repository: TaskRepository,
) : ViewModel() {
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    val tasksLiveData: MutableLiveData<List<String>> = MutableLiveData()

    fun getList() {
        val namesDisposable = repository.getDoneTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                tasksLiveData.value = result
            }
        compositeDisposable?.add(namesDisposable)
    }

    override fun onCleared() {
        compositeDisposable?.clear()
        compositeDisposable = null
        super.onCleared()
    }
}