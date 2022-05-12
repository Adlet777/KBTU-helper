package com.example.kbtu_helper.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.kbtu_helper.R
import com.example.kbtu_helper.model.user.User
import com.example.kbtu_helper.model.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import java.lang.Exception

class ProfileFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        val avatar_icon = view.findViewById<ImageView>(R.id.avatar_icon)
        val user_name = view.findViewById<TextView>(R.id.user_name)
        val user_email = view.findViewById<TextView>(R.id.user_email)
        val user_phone = view.findViewById<TextView>(R.id.user_phone)


        var users: User? = null
        val userFromDB = user?.let { mUserViewModel.getUserById(it.uid) }!!
        Log.d("USER RETREIVED! ", userFromDB.toString() + " + " + user.uid + "    " + users)

        user_name.text = userFromDB.name
        user_email.text = userFromDB.email
        user_phone.text = userFromDB.phone

        try {
            Picasso.get().load(userFromDB.image).into(avatar_icon)
        } catch (ex: Exception) {
            Picasso.get().load(R.drawable.ic_add_photo).into(avatar_icon)
        }


        return view
    }
}