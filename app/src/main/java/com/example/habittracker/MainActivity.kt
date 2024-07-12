package com.example.habittracker

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.habittracker.database.BackgroundTask
import com.example.habittracker.ui.theme.HabitTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userNameEt = findViewById<EditText>(R.id.dbuser_field)
        val passwordEt = findViewById<EditText>(R.id.dbpassword_field)

        fun onLogin() {
            val userName = userNameEt.text.toString()
            val password = passwordEt.text.toString()
            val type = "login"
            val backgroundTask = BackgroundTask(this)
            backgroundTask.execute(type, userName, password)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitTrackerTheme {
        Greeting("Android")
    }
}