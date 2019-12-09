package com.example.taski

import android.util.Log
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_does.view.*
import java.util.ArrayList

class TasksAdapter constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    val TAG = "TASK_ADAPTER"

    lateinit var context: Context
    lateinit var myTasks: ArrayList<MyTasks>

    constructor(c: Context?, p: ArrayList<MyTasks>) : this() {
        if (c != null) {
            context = c
        }
        myTasks = p
    }
    private var items: List<MyTasks> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, " onCreateViewHolder")
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_does, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, " onBindViewHolder")
        var id : Long
        when(holder) {
            is MyViewHolder -> {
                holder.bind(items[position])
            } //Dynamically brings items into view
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, " getItemCount : " + (items.size).toString())
        return items.size
    }

    fun submitList(taskList: List<MyTasks>) { //Takes List if MyTasks objects saves to item
        items = taskList
    } //items is used in onBindViewHolder

    class MyViewHolder constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleDoes = itemView.taskTitle
        val descriptionDoes = itemView.taskDescription
        val dateDoes = itemView.taskDate

        fun bind(tasks: MyTasks) {
            Log.d(TAG, " MyViewHolder Bind : " + (tasks.tasktitle))
            titleDoes.text = tasks.tasktitle
            descriptionDoes.text = tasks.taskdesc
            dateDoes.text = tasks.taskdate
        }
    }
}