package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : ComponentActivity() {

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
                Thread {
                    try {
                        val url = URL("http://10.0.2.2/BIS14_user_controller.php?action=getByEmail&key=$username")
                        val connection = url.openConnection() as HttpURLConnection
                        connection.requestMethod = "GET"
                        connection.doInput = true
                        connection.connect()

                        val responseCode = connection.responseCode
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            val responseStream = connection.inputStream
                            val response = responseStream.bufferedReader().use { it.readText() }

                            runOnUiThread {
                                if (response.contains(password)) {
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish() // Finaliza la actividad de login
                                } else {
                                    Toast.makeText(this, "Credenciales incorrectas.", Toast.LENGTH_LONG).show()
                                }
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this, "Error en el servidor.", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }.start()
            } else {
                Toast.makeText(this, "Por favor, ingrese todos los campos.", Toast.LENGTH_LONG).show()
            }
        }

        forgotPasswordLink.setOnClickListener {
            val email = usernameEditText.text.toString()

            if (email.isNotEmpty()) {
                Thread {
                    try {
                        val url = URL("http://10.0.2.2/BIS14_user_controller.php?action=resetPassword")
                        val connection = url.openConnection() as HttpURLConnection
                        connection.requestMethod = "POST"
                        connection.doOutput = true

                        val postData = "email=$email"
                        connection.outputStream.use { it.write(postData.toByteArray()) }

                        val responseCode = connection.responseCode
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            runOnUiThread {
                                Toast.makeText(this, "Correo de recuperación enviado a $email", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this, "Error al enviar correo de recuperación.", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }.start()
            } else {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico.", Toast.LENGTH_LONG).show()
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterUserActivity::class.java)
            startActivity(intent)
        }
    }
}
