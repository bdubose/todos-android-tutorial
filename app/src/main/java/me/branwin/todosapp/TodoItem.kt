package me.branwin.todosapp

data class Todo(
    val title: String,
    var isChecked: Boolean = false
)