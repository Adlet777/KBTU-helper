package com.example.kbtu_helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.stats.TagStat
import com.example.kbtu_helper.subjects.Subject

class SubjectsListAdapter: RecyclerView.Adapter<SubjectsListAdapter.MyViewHolder>() {

//    private var subjectsList = emptyList<Subject>()
    private var subjectsList = emptyList<TagStat>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_subject_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectsList[position]
        val name = holder.itemView.findViewById<TextView>(R.id.subject_name_text)
        val level = holder.itemView.findViewById<TextView>(R.id.subject_level_text)
        val tag = holder.itemView.findViewById<TextView>(R.id.subject_tag_text)
//        name.text = currentItem.name
        name.text = "Tag"
        level.text = currentItem.level.toString()
        tag.text = currentItem.tag

//        val rowLayout = holder.itemView.findViewById<TextView>(R.id.rowLayout)
//        rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToInfoFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }

//    fun setData(todo: List<Subject>) {
//        this.subjectsList = todo
//        notifyDataSetChanged()
//    }
//
//    fun getData(): List<Subject> {
//        return this.subjectsList
//    }
    fun setData(tagStat: List<TagStat>) {
    this.subjectsList = tagStat
    notifyDataSetChanged()
}

    fun getData(): List<TagStat> {
        return this.subjectsList
    }
}