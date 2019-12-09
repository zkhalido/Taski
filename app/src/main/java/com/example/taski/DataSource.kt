package com.example.taski

class DataSource{

    companion object{

        fun createDataSet(taskTitle: String, taskDesc: String, taskDate:String): MyTasks{
            val list = MyTasks()
            list.setTitletask(taskTitle)
            list.setDesctask(taskDesc)
            list.setDatetask(taskDate)
            return list
        }
    }
}