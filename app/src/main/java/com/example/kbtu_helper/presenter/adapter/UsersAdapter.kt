package com.example.kbtu_helper.presenter.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.presenter.adapter.UsersAdapter.MyHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.widget.TextView
import com.example.kbtu_helper.presenter.ChatActivity
import com.example.kbtu_helper.R
import com.example.kbtu_helper.model.user.User
import java.lang.Exception

class UsersAdapter(var context: Context, var userList: List<User>) :
    RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_users, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val externalUID = userList[position].uid
        val userImage = userList[position].image
        val userName = userList[position].name
        val userEmail = userList[position].email
        holder.mName.text = userName
        holder.mEmail.text = userEmail
        try {
            Picasso
                .get()
                .load(userImage)
                .placeholder(R.drawable.ic_default_avatar)
                .into(holder.mAvatar)
        } catch (e: Exception) {
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("externalUID", externalUID)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mAvatar: ImageView = itemView.findViewById(R.id.avatar_image_view)
        var mName: TextView = itemView.findViewById(R.id.name_text_view)
        var mEmail: TextView = itemView.findViewById(R.id.email_text_view)
    }
}