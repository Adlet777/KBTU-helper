package com.example.kbtu_helper.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.R
import com.example.kbtu_helper.model.stats.TagStat


class ProgressAdapter : RecyclerView.Adapter<ProgressAdapter.MyViewHolder>() {
    private var tagStatsList = emptyList<TagStat>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_progress_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tagStatsList[position]
        val level = holder.itemView.findViewById<TextView>(R.id.subject_level_text)
        val tag = holder.itemView.findViewById<TextView>(R.id.subject_tag_text)
        level.text = currentItem.level
        tag.text = currentItem.tag
    }

    override fun getItemCount(): Int {
        return tagStatsList.size
    }


    fun setData(tagStat: List<TagStat>) {
        this.tagStatsList = tagStat
        notifyDataSetChanged()
    }

    fun getData(): List<TagStat> {
        return this.tagStatsList
    }
}