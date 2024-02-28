package com.example.todolist.repository

import io.reactivex.rxjava3.core.Observable

class TaskRepository {
    fun getDeletedTasks(): Observable<List<String>> {
        val tasks = listOf("Receive a good mark", "Go to the gym", "Tell your crush about your feelings", "Study something new", "Go for a walk with my pet")
        return Observable.just(tasks)
    }
    fun getDoneTasks(): Observable<List<String>> {
        val tasks = listOf("Drink a cup of tea", "Eat a breakfast", "Brush your teeth", "Take a bag with you", "Say goodbye to your mama")
        return Observable.just(tasks)
    }
    fun getInProgressTasks(): Observable<List<String>> {
        val tasks = listOf("Go to the college", "Go to the college", "Study well", "Speak with your friend", "Think about your next day")
        return Observable.just(tasks)
    }
}