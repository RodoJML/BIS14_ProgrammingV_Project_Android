package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habittracker.database.controller.User_controller
import com.example.habittracker.database.model.User_model
import com.example.habittracker.ui.theme.HabitTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    // The context here is the context of the MainActivity
    val context = LocalContext.current
    var userList by remember { mutableStateOf<List<User_model>>(emptyList()) }
    var user by remember { mutableStateOf<User_model?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun fetchUsers() {
        User_controller(context).getAll(object : User_controller.getAllCallback {
            override fun onSuccess(userModels: List<User_model>) {
                userList = userModels
            }

            override fun onError(errorMessage: String) {
                TODO("Not yet implemented")
            }
        })
    }


    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Hello $name!")

        Button(onClick = { fetchUsers() }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Fetch Users")
        }

        if (errorMessage != null) {
            Text(text = "Error: $errorMessage", modifier = Modifier.padding(top = 16.dp))
        } else {
            userList.forEach { user ->
                Text(text = user.toString(), modifier = Modifier.padding(top = 8.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitTrackerTheme {
        Greeting("Android")
    }
}