package com.example.kbtu_helper.presenter.adapter

import android.content.Context
import android.text.format.DateFormat
import com.example.kbtu_helper.model.chats.Chat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.widget.TextView
import com.example.kbtu_helper.R
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception
import java.util.*

class ChatAdapter(var context: Context, var chatList: List<Chat>, var imageUrl: String) :
    RecyclerView.Adapter<ChatAdapter.MyHolder>() {
    var firebaseUser: FirebaseUser? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return if (viewType == MESSAGE_TYPE_SENDER) {
            val view = LayoutInflater.from(context).inflate(R.layout.row_chat_sender, parent, false)
            MyHolder(view)
        } else {
            val view =
                LayoutInflater.from(context).inflate(R.layout.row_chat_receiver, parent, false)
            MyHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val message = chatList[position].message
        val time = chatList[position].time
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = time.toLong()
        val dateTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString()
        holder.message_text_view.text = message
        holder.time_text_view.text = dateTime
        try {
            Picasso.get().load(imageUrl).into(holder.profile_image_view)
        } catch (e: Exception) {
        }
        if (position == chatList.size - 1) {
            if (chatList[position].isSeen) {
                holder.is_seen_text_view.text = "Seen"
            } else {
                holder.is_seen_text_view.text = "Delivered"
            }
        } else {
            holder.is_seen_text_view.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profile_image_view: ImageView
        var message_text_view: TextView
        var time_text_view: TextView
        var is_seen_text_view: TextView

        init {
            profile_image_view = itemView.findViewById(R.id.profile_image_view)
            message_text_view = itemView.findViewById(R.id.message_text_view)
            time_text_view = itemView.findViewById(R.id.time_text_view)
            is_seen_text_view = itemView.findViewById(R.id.message_sent_text_view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        return if (chatList[position].sender == firebaseUser!!.uid) {
            MESSAGE_TYPE_SENDER
        } else {
            MESSAGE_TYPE_RECEIVER
        }
    }

    companion object {
        private const val MESSAGE_TYPE_RECEIVER = 0
        private const val MESSAGE_TYPE_SENDER = 1
    }
}