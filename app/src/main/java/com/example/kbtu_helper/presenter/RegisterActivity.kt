package com.example.kbtu_helper.presenter

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kbtu_helper.R
import com.example.kbtu_helper.model.user.User
import com.example.kbtu_helper.model.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception


class RegisterActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.title = "Create account"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val mEmail = findViewById<EditText>(R.id.email_edit_text)
        val mPassword = findViewById<EditText>(R.id.password_edit_text)
        val mRegister = findViewById<Button>(R.id.register_activity_button)
        val mHaveAccountTextView = findViewById<TextView>(R.id.have_account_text_view)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        mAuth = FirebaseAuth.getInstance()

        mRegister.setOnClickListener {
            val email = mEmail.text.toString().trim()
            val password = mPassword.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mEmail.error = "Invalid email"
                mEmail.focusable
            } else if (password.length < 6) {
                mPassword.error = "Password must be at least 6 characters long"
                mPassword.focusable
            } else {
                registerUser(email, password)
            }
        }

        mHaveAccountTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun registerUser(email: String, password: String) {
        try {
            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth!!.currentUser

                        val email = user?.email.toString()
                        val uid = user?.uid.toString()

                        insertDataToDB(
                            uid,
                            email,
                            "User test name",
                            "+7 777 877 87 87",
                            "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png"
                        )


                        if (user != null) {
                            Toast.makeText(
                                this, "Registered " + user.email.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this, DashboardActivity::class.java))
                            finish()
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        } catch (e: Exception) {
            Toast.makeText(
                this, e.localizedMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun insertDataToDB(
        uid: String,
        email: String,
        name: String,
        phone: String,
        image: String
    ) {

        if (inputCheck(email, name, phone, image)) {
            val user = User(uid, email, name, phone, image)
            mUserViewModel.createUser(user)
            Log.d("DATABASE", "success USER CREATED" + user.toString())
            Toast.makeText(this, "Successfully created User task", Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(this, "Some values are not entered", Toast.LENGTH_LONG).show()
    }

    private fun inputCheck(
        email: String,
        name: String,
        phone: String,
        image: String
    ): Boolean {
        return !(TextUtils.isEmpty(email)
                && TextUtils.isEmpty(name)
                && TextUtils.isEmpty(phone)
                && TextUtils.isEmpty(image))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}