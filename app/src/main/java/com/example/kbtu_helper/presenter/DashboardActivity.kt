package com.example.kbtu_helper.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kbtu_helper.R
import com.example.kbtu_helper.view.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private var fireBaseAuth: FirebaseAuth? = null
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.title = "Home"
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.content, homeFragment, "")
            .commit()

        fireBaseAuth = FirebaseAuth.getInstance()

        navigationView = findViewById(R.id.navigation)

        navigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_home -> {
                    supportActionBar?.title = "Home"
                    val newHomeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, newHomeFragment, "")
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_profile -> {
                    supportActionBar?.title = "Profile"
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, profileFragment, "")
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_users -> {
                    supportActionBar?.title = "Users"
                    val usersFragment = UsersFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, usersFragment, "")
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_subjects -> {
                    supportActionBar?.title = "Subjects"
                    val subjectsFragment = SubjectsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, subjectsFragment, "")
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_chat -> {
                    supportActionBar?.title = "Progress"
                    val progressFragment = ProgressFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, progressFragment, "")
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


    }


    private fun checkUserStatus() {
        val user = fireBaseAuth?.currentUser

        if (user != null) {
//            mProfileTextView?.text = user.email.toString()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        checkUserStatus()
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_logout) {
            fireBaseAuth?.signOut()
            checkUserStatus()
        }
        return super.onOptionsItemSelected(item)
    }
}