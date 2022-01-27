package com.example.mydiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_diary_details.*

class DiaryDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_details)

        val id = intent.getIntExtra("ID",0)
        val title = arrayListOf<String>()
        val date = arrayListOf<String>()
        val content = arrayListOf<String>()
        val dbHelper = MyDatabaseHelper(this,"Diary.db",1)
        val db = dbHelper.readableDatabase
        val cursor = db.query("Diary", null,"id = ?", arrayOf(id.toString()),null,null,null)
        if (cursor.moveToFirst()){
            do {
                title.add(cursor.getString(cursor.getColumnIndex("title")))
                date.add(cursor.getString(cursor.getColumnIndex("date")))
                content.add(cursor.getString(cursor.getColumnIndex("content")))
            }while (cursor.moveToNext())
        }
        cursor.close()
        if (!title.isEmpty()){
            title_detail.text = title[0]
            date_detail.text = date[0]
            content_detail.text = content[0]
        }
    }
}