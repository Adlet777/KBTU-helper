package com.example.kbtu_helper.presenter

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kbtu_helper.R

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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}