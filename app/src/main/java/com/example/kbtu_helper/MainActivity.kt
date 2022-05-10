package com.example.kbtu_helper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRegisterButton = findViewById<Button>(R.id.register_button)
        val mLoginButton = findViewById<Button>(R.id.login_button)

        mRegisterButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        mLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}