package com.example.todolist_viewmodellivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: LiveData<MutableList<Task>> = repository.getTasks()

    fun addTask(task: Task) {
        repository.addTask(task)
    }

    fun removeTask(task: Task) {
        repository.removeTask(task)
    }
}