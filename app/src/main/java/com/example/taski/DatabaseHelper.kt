package com.example.taski

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    var TAG = "DATABASE_HELPER"

    companion object Factory{
        var DATABASE_VER = 1
        var DATABASE_NAME = "TASKS.db"
        var TABLE_NAME = "TASKS"
        var COL1 = "ID"
        var COL2 = "title"
        var COL3 = "description"
        var COL4 = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        var createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData (itemTitle: String, itemDesc: String, itemDate: String): Boolean {
        Log.d(TAG, " addData")

        var db: SQLiteDatabase = this.writableDatabase
        var contextValues = ContentValues()

        contextValues.put(COL2, itemTitle)
        contextValues.put(COL3, itemDesc)
        contextValues.put(COL4, itemDate)

        Log.d(TAG, " addData: Adding " + itemTitle +
                ", " + itemDesc + ", " + itemDate + ", " +
                " to " + TABLE_NAME)

        var result = db.insert(TABLE_NAME, null, contextValues)

        //if data inserted incorrectly will return -1
        if (result.equals(-1)) {
            return false
        }
        return true
    }

    fun getData(): Cursor {
        var db : SQLiteDatabase = this.writableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var data: Cursor = db.rawQuery(query, null)
        return data
    }

    fun deleteDatabase() {
        Log.d(TAG, " deleteDatabase")
        var db : SQLiteDatabase = this.writableDatabase
        db.execSQL("delete from " + TABLE_NAME)
        //db.execSQL("TRUNCATE TABLE " + TABLE_NAME)
        db.close()
    }
}