package com.example.todolist_viewmodellivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TaskRepository {
    private val tasks = MutableLiveData<MutableList<Task>>(mutableListOf())

    fun getTasks(): LiveData<MutableList<Task>> {
        return tasks
    }

    fun addTask(task: Task) {
        val currentTasks = tasks.value ?: mutableListOf()
        currentTasks.add(task)
        tasks.value = currentTasks
    }

    fun removeTask(task: Task) {
        val currentTasks = tasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.remove(task)
        tasks.value = currentTasks
    }
}
