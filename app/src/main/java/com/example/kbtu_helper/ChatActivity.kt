package com.example.kbtu_helper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.chats.Chat
import com.example.kbtu_helper.chats.ChatViewModel
import com.example.kbtu_helper.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var firebaseAuthorization: FirebaseAuth
    private lateinit var externalUID: String
    private lateinit var myUID: String
    private lateinit var externalImage: String
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mChatViewModel: ChatViewModel
    private lateinit var messageEditText: EditText
    private var chatList: List<Chat> = emptyList<Chat>()
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mChatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.title = ""
        recyclerView = findViewById<RecyclerView>(R.id.chat_recycler_view)
        val profile = findViewById<ImageView>(R.id.user_image_view)
        val name = findViewById<TextView>(R.id.name_text_view)
        val status = findViewById<TextView>(R.id.status_text_view)
        messageEditText = findViewById<EditText>(R.id.send_message)
        val sendButton = findViewById<ImageButton>(R.id.send_button)


        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager


        val intent = intent
        externalUID = intent.getStringExtra("externalUID").toString()

        val userFromDB = mUserViewModel.getUserById(externalUID)
        name.text = userFromDB.name
        externalImage = userFromDB.image
        try {
            Picasso.get().load(externalImage).placeholder(R.drawable.ic_default_avatar).into(profile)
        } catch (e: Exception) {
            Picasso.get().load(R.drawable.ic_default_avatar).into(profile)
        }

        sendButton.setOnClickListener {
            val message = messageEditText.text.toString().trim()
            if (TextUtils.isEmpty(message)) {
                Toast.makeText(
                    this, "Message is empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {       
                sendMessage(message)
            }
        }

        firebaseAuthorization = FirebaseAuth.getInstance()

        readMessages()
        seenMessage()
    }

    private fun seenMessage() {
        val allChatsFromDB = mChatViewModel.getAllChats()

        allChatsFromDB.forEach {
                chat ->
            if (chat.receiver == myUID && chat.sender == externalUID) {
                mChatViewModel.updateIsSeenById(chat.uid, true)
            }
        }
    }

    private fun readMessages() {
        val allChatsFromDB = mChatViewModel.getAllChats()
        checkUserStatus()
        chatList = emptyList()
        chatList = chatList.toMutableList()

        allChatsFromDB.forEach {
            chat ->
                if (chat.receiver == myUID && chat.sender == externalUID ||
                        chat.receiver == externalUID && chat.sender == myUID
                ) {
                    (chatList as MutableList<Chat>).add(chat)
                }

            chatAdapter = ChatAdapter(this, chatList, externalImage)
            chatAdapter.notifyDataSetChanged()
            recyclerView.adapter = chatAdapter

        }
    }

    private fun sendMessage(message: String) {

        val time = System.currentTimeMillis().toString()
        val chat = Chat(UUID.randomUUID().toString(), myUID, externalUID, message, time, false)
        mChatViewModel.createChat(chat)

        messageEditText.setText("")
        readMessages()
    }

    fun checkUserStatus() {
        val user = firebaseAuthorization.currentUser
        if (user != null) {
            myUID = user.uid
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_logout) {
            firebaseAuthorization.signOut()
            checkUserStatus()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        checkUserStatus()
        super.onStart()
    }
}