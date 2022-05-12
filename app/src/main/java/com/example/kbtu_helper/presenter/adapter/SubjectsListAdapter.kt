package com.example.kbtu_helper.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.R
import com.example.kbtu_helper.model.subjects.Subject

class SubjectsListAdapter : RecyclerView.Adapter<SubjectsListAdapter.MyViewHolder>() {

    private var subjectsList = emptyList<Subject>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_subject_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectsList[position]
        val name = holder.itemView.findViewById<TextView>(R.id.subject_name_text)
        val level = holder.itemView.findViewById<TextView>(R.id.subject_level_text)
        val tag = holder.itemView.findViewById<TextView>(R.id.subject_tag_text)
        name.text = currentItem.name
        level.text = currentItem.level.toString()
        tag.text = currentItem.tag

    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }


    fun setData(tagStat: List<Subject>) {
        this.subjectsList = tagStat
        notifyDataSetChanged()
    }

    fun getData(): List<Subject> {
        return this.subjectsList
    }
}