package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginactivity) // Inflar el archivo XML

        // Obtener referencias a los elementos de la UI
        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login_button)
        val registerButton: Button = findViewById(R.id.register_button)
        val forgotPasswordLink: TextView = findViewById(R.id.forgot_password_link)

        // Configurar acciones de los botones
        loginButton.setOnClickListener {
            // Manejar la acción de iniciar sesión aquí
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Lógica de inicio de sesión
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, register_user::class.java)
            startActivity(intent)
        }

        forgotPasswordLink.setOnClickListener {
            // Manejar la acción de olvido de contraseña aquí
        }
    }
}
