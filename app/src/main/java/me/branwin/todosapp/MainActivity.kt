package me.branwin.todosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import me.branwin.todosapp.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())

        val self = this
        binding.apply {
            rvTodoItems.adapter = todoAdapter
            rvTodoItems.layoutManager = LinearLayoutManager(self)

            btnAddTodo.setOnClickListener {
                val todoTitle = etTodoTitle.text.toString()

                if (todoTitle.isNotEmpty()) {
                    todoAdapter.addTodo(Todo(todoTitle))
                    etTodoTitle.text.clear()
                }
            }

            btnDeleteDone.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }
        }
    }
}