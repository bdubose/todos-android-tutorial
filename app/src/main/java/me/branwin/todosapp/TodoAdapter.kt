package me.branwin.todosapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.branwin.todosapp.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeIf {
            it.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tv: TextView, isChecked: Boolean) {
        if (isChecked) {
            tv.paintFlags = tv.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tv.paintFlags = tv.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.binding.apply {
            tvTodoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked

            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                currentTodo.isChecked = isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        val branwin = Person("branwin")
        val gage = Person("gage")
        branwin loves gage
        return todos.size
    }


}

class Person(val name: String) {
    infix fun loves(p1: Person) {
        println("$name loves ${p1.name}")
    }
}