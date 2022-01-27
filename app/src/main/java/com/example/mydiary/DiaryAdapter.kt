package com.example.mydiary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter (val diaryList: List<String>,val idList: List<Int>) : RecyclerView.Adapter<DiaryAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val diaryTitle: TextView = view.findViewById(R.id.diary_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.diary_item, parent, false)

        /*注册点击事件*/
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            val context: Context = view.context
            val intent = Intent(context,DiaryDetails::class.java)
            intent.putExtra("ID",idList[position])
            context.startActivity(intent)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = diaryList[position]
        holder.diaryTitle.text = diary
    }

    override fun getItemCount() = diaryList.size
}