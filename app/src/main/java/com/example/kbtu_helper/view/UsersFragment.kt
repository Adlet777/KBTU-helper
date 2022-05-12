package com.example.kbtu_helper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.R
import com.example.kbtu_helper.presenter.adapter.UsersAdapter
import com.example.kbtu_helper.model.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class UsersFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_users, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.users_recycler_view)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        getAllUsers();

        return view
    }

    private fun getAllUsers() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentDBUser = currentUser?.let { mUserViewModel.getUserById(it.uid) }

        val usersList = mUserViewModel.getAllUsers().toMutableList()


        if (currentDBUser != null) {
            usersList.removeIf { user -> user.uid == currentDBUser.uid }
        }

        val adapter = this.context?.let { UsersAdapter(it, usersList) }
        recyclerView.adapter = adapter
    }
}