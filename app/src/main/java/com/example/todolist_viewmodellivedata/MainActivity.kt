package com.example.todolist_viewmodellivedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi Repository dan ViewModel
        val repository = TaskRepository()
        val factory = ViewModelFactory(repository)
        viewModel = viewModels<TaskViewModel> { factory }.value

        // Inisialisasi RecyclerView
        taskAdapter = TaskAdapter(mutableListOf()) { task ->
            viewModel.removeTask(task)
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        titleTextView = findViewById(R.id.title)

        // Observe LiveData
        viewModel.tasks.observe(this, Observer { tasks ->
            tasks?.let { taskAdapter.updateTasks(it) }
        })

        // Tambahkan Tugas
        val addButton: Button = findViewById(R.id.addButton)
        val taskInput: EditText = findViewById(R.id.taskInput)

        addButton.setOnClickListener {
            val taskName = taskInput.text.toString()
            if (taskName.isNotEmpty()) {
                viewModel.addTask(Task(id = (0..1000).random(), name = taskName))
                taskInput.text.clear()
            }
        }
    }
}