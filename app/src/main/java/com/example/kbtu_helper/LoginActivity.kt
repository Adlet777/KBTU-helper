package com.example.kbtu_helper

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kbtu_helper.user.User
import com.example.kbtu_helper.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception


class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Login"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mAuth = FirebaseAuth.getInstance()

        val mEmail = findViewById<EditText>(R.id.email_edit_text)
        val mPassword = findViewById<EditText>(R.id.password_edit_text)
        val notHaveAccount = findViewById<TextView>(R.id.not_have_account_text_view)
        val mLogin = findViewById<Button>(R.id.login_activity_button)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mLogin.setOnClickListener  {
            val email = mEmail.text.toString()
            val password = mPassword.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())   {
                mEmail.error = "Invalid email"
                mEmail.focusable
            }
            else {
                loginUser(email, password)
            }
        }
        notHaveAccount.setOnClickListener  {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun loginUser(email: String, password: String) {
        try {
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth!!.currentUser

                        val email = user?.email.toString()
                        val uid = user?.uid.toString()

                        insertDataToDB(uid, email, "", "", "")


                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this, "Authentication failed",
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
        image: String) {

        if (inputCheck(email, name, phone, image)) {
            val user = User(uid, email, name, phone, image)
            mUserViewModel.createUser(user)
            Toast.makeText(this, "Successfully created User task", Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(this, "Some values are not entered", Toast.LENGTH_LONG).show()
    }

    private fun inputCheck(email: String,
                           name: String,
                           phone: String,
                           image: String
    ): Boolean {
        return !(TextUtils.isEmpty(email)
                && TextUtils.isEmpty(name)
                && TextUtils.isEmpty(phone)
                && TextUtils.isEmpty(image))
    }
}