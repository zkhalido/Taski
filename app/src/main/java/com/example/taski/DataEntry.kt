package com.example.taski

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DataEntry : AppCompatActivity() {

    private lateinit var mDataBaseHelper: DatabaseHelper
    private lateinit var btnSave: Button
    private lateinit var editTitle: EditText
    private lateinit var editDesc: EditText
    private lateinit var editDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_entry)
        supportActionBar?.hide() //hides action bar

        mDataBaseHelper = DatabaseHelper(this)
        editTitle = findViewById<EditText>(R.id.editTextTitle)
        editDesc = findViewById<EditText>(R.id.editTextDescription)
        editDate = findViewById<EditText>(R.id.editTextDate)
        btnSave = findViewById<Button>(R.id.data_entry_saveBtn)

        btnSave.setOnClickListener {

            var newTitle = editTitle.text.toString()
            var newDesc = editDesc.text.toString()
            var newDate = editDate.text.toString()

            AddData(newTitle, newDesc, newDate)

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun AddData(newTitle: String, newDesc: String, newDate: String) {
        var insertData: Boolean = mDataBaseHelper.addData(newTitle, newDesc, newDate)

        if(insertData) {
                Toast.makeText(this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show()
        } else {
                Toast.makeText(this, "Data Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}