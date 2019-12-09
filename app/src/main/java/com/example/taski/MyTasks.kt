package com.example.taski

class MyTasks {
    lateinit var tasktitle : String
    lateinit var taskdesc : String
    lateinit var taskdate : String

    constructor() {

    }

    constructor(tasktitle: String, taskdesc: String, taskdate: String) {
        this.tasktitle = tasktitle
        this.taskdesc = taskdesc
        this.taskdate = taskdate
    }

    fun getTitletask(): String {
        return tasktitle
    }
    fun setTitletask(titletask:String) {
        this.tasktitle = titletask
    }
    fun getDesctask(): String {
        return taskdesc
    }
    fun setDesctask(desctask:String) {
        this.taskdesc = desctask
    }
    fun getDatetask(): String {
        return taskdate
    }
    fun setDatetask(datetask:String) {
        this.taskdate = datetask
    }
}