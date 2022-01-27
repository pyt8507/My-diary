package com.example.mydiary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {
    private val createDiary = "create table Diary (" +
            "id integer primary key autoincrement," +
            "title text," +
            "date text," +
            "content text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createDiary)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}