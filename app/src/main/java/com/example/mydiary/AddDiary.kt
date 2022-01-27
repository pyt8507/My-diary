package com.example.mydiary

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_diary.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddDiary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diary)

        val dbHelper = MyDatabaseHelper(this,"Diary.db",1)
        val db = dbHelper.writableDatabase

        saveButton.setOnClickListener {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatted = current.format(formatter)

            val values = ContentValues().apply {
                put("title",title_edit.text.toString())
                put("date",formatted)
                put("content",content_edit.text.toString())
            }
            db.insert("Diary", null, values)
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}