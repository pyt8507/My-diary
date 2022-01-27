package com.example.mydiary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this,AddDiary::class.java)
            startActivity(intent)
        }




    }

    override fun onResume() {
        super.onResume()
        val dbHelper = MyDatabaseHelper(this,"Diary.db",1)
        val db = dbHelper.readableDatabase
        val titles = arrayListOf<String>()
        val ids = arrayListOf<Int>()
        val cursor = db.query("Diary", null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do {
                titles.add(cursor.getString(cursor.getColumnIndex("title")))
                ids.add(cursor.getInt(cursor.getColumnIndex("id")))
            }while (cursor.moveToNext())
        }
        cursor.close()

        val layoutManager = LinearLayoutManager(this)
        recycleView.layoutManager = layoutManager
        val adapter = DiaryAdapter(titles,ids)
        recycleView.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this,AddDiary::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}