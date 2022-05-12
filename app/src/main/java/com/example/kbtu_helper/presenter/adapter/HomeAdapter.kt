package com.example.kbtu_helper.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.R
import com.example.kbtu_helper.presenter.db.retrofit.ArticleItem
import com.squareup.picasso.Picasso


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var articlesList = emptyList<ArticleItem>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_home_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = articlesList[position]
        val title = holder.itemView.findViewById<TextView>(R.id.title_text_home)
        val image = holder.itemView.findViewById<ImageView>(R.id.image_view_home)
        val articleText = holder.itemView.findViewById<TextView>(R.id.article_full_text)
        title.text = currentItem.title

        try {
            Picasso.get()
                .load(currentItem.image)
                .placeholder(R.drawable.ic_default_avatar)
                .into(image)
        } catch (e: Exception) {
        }

        articleText.text = currentItem.text
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }


    fun setData(tagStat: List<ArticleItem>) {
        this.articlesList = tagStat
        notifyDataSetChanged()
    }

    fun getData(): List<ArticleItem> {
        return this.articlesList
    }

}