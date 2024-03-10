package com.example.todolist.repository

import com.example.todolist.data.database.DataBase
import com.example.todolist.data.datamodel.Task

class TaskRepository {
    private val dataBase: DataBase = DataBase.getInstance()
    fun getDeletedTasks(): ArrayList<Task> {
        return dataBase.getListDeletedTasks()
    }
    fun getDoneTasks(): ArrayList<Task> {
        return dataBase.getListDoneTasks()
    }
    fun getInProgressTasks(): ArrayList<Task> {
        return dataBase.getListInProgressTasks()
    }

    fun addNewTask(task: Task){
        dataBase.addNewTask(task)
    }
}