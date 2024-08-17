package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.habittracker.ui.theme.HabitTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitTrackerTheme {
                SignInScreen(
                    onSignIn = {
                        // Manejar la acción de iniciar sesión aquí
                    },
                    onForgotPassword = {
                        // Manejar la acción de olvido de contraseña aquí
                    },
                    onSignUp = {
                        val intent = Intent(this@MainActivity, RegisterUserActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun SignInScreen(
    onSignIn: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSignIn,
            modifier = Modifier.fillMaxWidth()
        ) {

        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgot Password?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { onForgotPassword() }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSignUp,
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}
