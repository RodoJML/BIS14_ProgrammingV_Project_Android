package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.net.HttpURLConnection
import java.net.URL
import com.example.habittracker.database.controller.user_controller
import com.example.habittracker.database.model.user_model

class LoginActivity : ComponentActivity() {
    private var user: user_model? = null
    // The context here is the context of the MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginactivity) // Inflar el archivo XML

        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login_button)
        val registerButton: Button = findViewById(R.id.register_button)
        val forgotPasswordLink: TextView = findViewById(R.id.forgot_password_link)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                fetchUsersById(username)
                if (this.user!= null) {
                    val intent = Intent(this@LoginActivity, TrackingActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Usuario no encontrado.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese todos los campos.", Toast.LENGTH_LONG).show()
            }
        }

        forgotPasswordLink.setOnClickListener {
            val email = usernameEditText.text.toString()

            if (email.isNotEmpty()) {
               // debe hacerse usando logica controlador
            } else {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico.", Toast.LENGTH_LONG).show()
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchUsersById(id:String) {
        user_controller(this).getById(id, object : user_controller.getByIdCallback {
            override fun onSuccess(userModel: user_model) {
                user = userModel
             // necesito entender este runOnUiThread
             //   runOnUiThread {
                    //user = userModel

               // }
            }

            override fun onError(errorMessage: String) {
                Log.d("Error", errorMessage)
               // showError(errorMessage) // Show error message in UI
            }
        })
    }
}
