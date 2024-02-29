package com.example.todolist.repository


class TaskRepository {
    fun getDeletedTasks(): List<String> {
        return listOf("Receive a good mark", "Go to the gym", "Tell your crush about your feelings", "Study something new", "Go for a walk with my pet")
    }
    fun getDoneTasks(): List<String> {
        return listOf("Drink a cup of tea", "Eat a breakfast", "Brush your teeth", "Take a bag with you", "Say goodbye to your mama")
    }
    fun getInProgressTasks(): List<String> {
        return listOf("Make a good day", "Go to the college", "Study well", "Speak with your friend", "Think about your next day")
    }
}