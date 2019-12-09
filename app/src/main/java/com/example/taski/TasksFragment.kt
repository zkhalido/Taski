package com.example.taski

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

val TAG = "TASK_FRAGMENT"

class TasksFragment : Fragment() {

    lateinit var mDatabsaeHelper: DatabaseHelper
    lateinit var ourtasks: RecyclerView
    lateinit var list:ArrayList<MyTasks>
    lateinit var taskAdapter: TasksAdapter
    lateinit var fabButtonAdd: FloatingActionButton
    lateinit var fabButtonDeleted: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_tasks, container, false)
        Log.d(TAG, " onCreateView")

        //Initialize DatabaseHelper, Adapter, and List
        mDatabsaeHelper = DatabaseHelper(context)
        taskAdapter = TasksAdapter()
        list = ArrayList()

        //recyclerView Item
        ourtasks = rootView.findViewById(R.id.recycler_view)

        //Assigning Adapter to recyclerView
        taskAdapter = TasksAdapter()
        ourtasks.adapter = taskAdapter

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        ourtasks.layoutManager = layoutManager

        fabButtonAdd = rootView.findViewById(R.id.addTaskButton)
        fabButtonDeleted = rootView.findViewById(R.id.delTaskButton)

        fabButtonDeleted.setOnClickListener {
            Log.d(TAG, " fabButtonDeleted Clicked")
            mDatabsaeHelper.deleteDatabase()
            mDatabsaeHelper = DatabaseHelper(context)
            taskAdapter = TasksAdapter()
            list = ArrayList()

            ourtasks = rootView.findViewById(R.id.recycler_view)

            taskAdapter = TasksAdapter()
            ourtasks.adapter = taskAdapter

            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
            ourtasks.layoutManager = layoutManager
        }

        fabButtonAddClick()
        addDataSet()

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, " onCreate")

    }

    fun addDataSet() {
        Log.d(TAG, " addDataSet")

        var data: Cursor = mDatabsaeHelper.getData()
        var arrayTaskString = arrayOf("temp", "temp", "temp")
        val taskListData = ArrayList<MyTasks>()
        data.moveToLast()
        data.moveToNext()
        while (data.moveToPrevious()) {
            Log.d(TAG, " data.MoveToNext")
            arrayTaskString[0] = data.getString(1)
            arrayTaskString[1] = data.getString(2)
            arrayTaskString[2] = data.getString(3)

            val taskListItem: MyTasks = DataSource.createDataSet(arrayTaskString[0], arrayTaskString[1], arrayTaskString[2])
            taskListData.add(taskListItem)
        }

        taskAdapter.submitList(taskListData)
    }

    fun fabButtonAddClick() {
        fabButtonAdd.setOnClickListener {
            Log.d(TAG, " fabButtonClicked")
            val intent = Intent(context, DataEntry::class.java)
            startActivity(intent)
        }
    }
}