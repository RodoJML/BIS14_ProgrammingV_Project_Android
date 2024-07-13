package com.example.habittracker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.habittracker.database.BackgroundTask

class MainActivity : ComponentActivity() {
    private lateinit var UsernameEt: EditText
    private lateinit var PasswordEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logindb_layout)

        UsernameEt = findViewById<EditText>(R.id.dbuser_field)
        PasswordEt = findViewById<EditText>(R.id.dbpassword_field)

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            onLogin(it)
            // The "it" in this case is the View that was clicked
        }
    }

    fun onLogin(view: View) {
        val userName = UsernameEt.text.toString()
        val password = PasswordEt.text.toString()
        val type = "login"
        val backgroundTask = BackgroundTask(this)
        backgroundTask.execute(type, userName, password)
    }
}


