package com.example.todolist.data.database

import com.example.todolist.data.datamodel.Task

class DataBase {
    private val listOfInProgressTasks: ArrayList<Task> = ArrayList()
    private val listOfDoneTasks: ArrayList<Task> = ArrayList()
    private val listOfDeletedTasks: ArrayList<Task> = ArrayList()

    companion object{
        private var instance: DataBase? = null
        fun getInstance(): DataBase {
            if (instance == null){
                instance = DataBase()
            }
            return instance as DataBase
        }
    }

    fun getListInProgressTasks(): ArrayList<Task>{
        return ArrayList(listOfInProgressTasks)
    }
    fun getListDoneTasks(): ArrayList<Task>{
        return ArrayList(listOfDoneTasks)
    }
    fun getListDeletedTasks(): ArrayList<Task>{
        return ArrayList(listOfDeletedTasks)
    }

    fun addNewTask(task: Task){
        listOfInProgressTasks.add(task)
    }
    fun addToDeletedTasks(task: Task){
        listOfDeletedTasks.add(task)
    }
    fun addToDoneTasks(task: Task){
        listOfDoneTasks.add(task)
    }
}